package com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorSendEmailCertificationUseCase

class MentorRegistViewModel(
        private val mentorEmailCertificationCheckUseCase: MentorEmailCertificationCheckUseCase,
        private val mentorSendEmailCertificationUseCase: MentorSendEmailCertificationUseCase
) : ViewModel() {

    var isEmailChecked = MutableLiveData<Boolean>(false)

    var progressBarVisibility = MutableLiveData<Boolean>()

    var Is_mail_send = MutableLiveData<Boolean>()  // 메일 성공적으로 전송했는지
    var Is_Certified = MutableLiveData<Boolean>() // 메일 인증 했는지 최종 체크

    var MENTOR_EMAIL = ""


    // 메일 보내기
    @SuppressLint("CheckResult")
    fun sendMentorEmail(){
        progressBarVisibility.value = true

        mentorSendEmailCertificationUseCase.sendEmailCertification(Constants_gitignore.USER_TOKEN, MENTOR_EMAIL).subscribe(
            {
                if(it.code() == 200){
                    Log.e("AppTest", "MentorRegistViewModel/ 인증 메일 전송 성공, \n" +
                            "응답메시지 : ${it.message()}")

                    Is_mail_send.value = true
                }
                else{
                    Log.e("AppTest", "MentorRegistViewModel/ 인증 메일 전송 실패, status code : ${it.code()}")
                    Is_mail_send.value = false
                }

                progressBarVisibility.value = false
            },
            {
                throwable -> Log.e("AppTest", "인증 메일 전송 error ${throwable}")
                progressBarVisibility.value = false
                Is_mail_send.value = false
            }


        )

    }


    @SuppressLint("CheckResult")
    fun checkMentorEmailCertification(){
        progressBarVisibility.value = true
        mentorEmailCertificationCheckUseCase.getMentorEmailCertificationResult(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "MentorRegistViewModel/ 이메일 인증 여부 확인 성공!")

                            Constants.IS_CERTIFIED_MENTOR = it.body()!!.certifiedMentor
                            Log.e("AppTest", "MentorRegistViewModel/ 인증 여부 : ${Constants.IS_CERTIFIED_MENTOR}")

                        Is_Certified.value = true // 인증 여부 확인 성공 -> 다음 화면으로 넘어가게 된다 -> 수정하기!!

                    }
                    else{
                        Log.e("AppTest", "MentorRegistViewModel/ 이메일 인증 여부 확인 실패, status code : ${it.code()}")
                        Is_Certified.value = false
                    }
                    progressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                    progressBarVisibility.value = false
                    Is_Certified.value = false
                }
        )
    }
}