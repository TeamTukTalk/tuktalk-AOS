package com.example.tuktalk.presentation.mypage.mentor.mentorRegist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class MentorRegistViewModel(
        private val mentorEmailCertificationCheckUseCase: MentorEmailCertificationCheckUseCase
) : ViewModel() {

    var isEmailChecked = MutableLiveData<Boolean>(false)

    var progressBarVisibility = MutableLiveData<Boolean>()
    var Is_Certified = MutableLiveData<Boolean>()

    fun sendMentorEmail(){

        isEmailChecked.value = true
    }


    @SuppressLint("CheckResult")
    fun checkMentorEmailCertification(){
        progressBarVisibility.value = true
        mentorEmailCertificationCheckUseCase.getMentorEmailCertificationResult(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "MentorRegistViewModel/ 이메일 인증 여부 확인 성공!")

                            Constants.IS_CERTIFIED_MENTOR = it.body()!!.certifiedMentor
                            Log.e("AppTest", "MentorRegistViewModel/ 인증 여부 : ${it.body()!!.certifiedMentor}")

                        Is_Certified.value = true // 인증 성공 -> 다음 화면으로 넘어가게 된다

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