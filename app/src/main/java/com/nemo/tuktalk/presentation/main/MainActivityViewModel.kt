package com.nemo.tuktalk.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase

class MainActivityViewModel(
    private val mentorEmailCertificationCheckUseCase: MentorEmailCertificationCheckUseCase
): ViewModel() {

    var progressBarVisibility = MutableLiveData<Boolean>()
    var isCertificationCheckSuccess = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun checkMentorCertified(){
        progressBarVisibility.value = true

        mentorEmailCertificationCheckUseCase.getMentorEmailCertificationResult(Constants_gitignore.USER_TOKEN).subscribe(
            {
                if(it.code() == 200){
                    Log.e("AppTest", "MainActivityViewModel/ 이메일 인증 여부 확인 성공!")

                    Constants.IS_CERTIFIED_MENTOR = it.body()!!.certifiedMentor
                    Log.e("AppTest", "MainActivityViewModel/ 인증 여부 : ${Constants.IS_CERTIFIED_MENTOR}")

                    isCertificationCheckSuccess.value = true // 인증 여부 확인 성공

                }
                else{
                    Log.e("AppTest", "MainActivityViewModel/ 이메일 인증 여부 확인 실패, status code : ${it.code()}")
                    isCertificationCheckSuccess.value = false
                }
                progressBarVisibility.value = false
            },
            {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                progressBarVisibility.value = false
                isCertificationCheckSuccess.value = false
            }
        )

    }
}