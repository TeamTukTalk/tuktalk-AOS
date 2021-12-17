package com.nemo.tuktalk.presentation.mypage.account

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.mentee.change.MenteeToMentorUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase

class AccountOptionViewModel(
        private val mentorEmailCertificationCheckUseCase: MentorEmailCertificationCheckUseCase,
        private val menteeToMentorUseCase: MenteeToMentorUseCase
): ViewModel() {

    var progressBarVisibility = MutableLiveData<Boolean>()
    var isCertificationCheckSuccess = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun checkMentorCertified(){
        progressBarVisibility.value = true

        mentorEmailCertificationCheckUseCase.getMentorEmailCertificationResult(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "AccountOptionViewModel/ 이메일 인증 여부 확인 성공!")

                        Constants.IS_CERTIFIED_MENTOR = it.body()!!.certifiedMentor
                        Log.e("AppTest", "AccountOptionViewModel/ 인증 여부 : ${Constants.IS_CERTIFIED_MENTOR}")

                        isCertificationCheckSuccess.value = true // 인증 여부 확인 성공

                    }
                    else{
                        Log.e("AppTest", "AccountOptionViewModel/ 이메일 인증 여부 확인 실패, status code : ${it.code()}")
                        isCertificationCheckSuccess.value = false
                    }
                    progressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "AccountOptionViewModel/ 이메일 인증 여부 확인 오류, ${throwable}")
                    progressBarVisibility.value = false
                    isCertificationCheckSuccess.value = false
                }
        )

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    // 멘티 -> 멘토 전환 api

    var progressBarVisibility_change = MutableLiveData<Boolean>()
    var isMenteeToMentorSuccess = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun menteeToMentor(){

        progressBarVisibility_change.value = true

        menteeToMentorUseCase.menteeToMentor(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){

                        Log.e("AppTest", "AccountOptionViewModel/ 멘티->멘토 전환 성공")
                        isMenteeToMentorSuccess.value = true
                    }
                    else{
                        Log.e("AppTest", "AccountOptionViewModel/ 멘티->멘토 전환 실패")
                        isMenteeToMentorSuccess.value = false
                    }

                    progressBarVisibility_change.value = false
                },
                {
                    throwable -> Log.e("AppTest", "AccountOptionViewModel/ 멘티->멘토 전환 오류, ${throwable}")
                    progressBarVisibility_change.value = false
                    isMenteeToMentorSuccess.value = false
                }
        )
    }
}