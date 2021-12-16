package com.nemo.tuktalk.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase
import com.nemo.tuktalk.domain.usecase.user.GetUserInfoUseCase

class MainActivityViewModel(
    private val mentorEmailCertificationCheckUseCase: MentorEmailCertificationCheckUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
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

    ////////////////////////////////////////////////////////

    // 로그인 후 멘토아이디 위해 정보 가져오기 수행

    var progressBarVisibility_userinfo = MutableLiveData<Boolean>()
    var isGetUserInfoSuccess = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getUserInfo(){
        progressBarVisibility_userinfo.value = true
        getUserInfoUseCase.getUserInfo(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "MainActivityViewModel/ 유저정보 조회 성공")

                        var role = it.body()!!.role

                        if(role.equals("MENTOR")) {
                            Constants.USER_MENTOR_ID = it.body()!!.mentorId
                            Log.e("AppTest", "MainActivityViewModel/ 현재 로그인 멘토 아이디 : ${Constants.USER_MENTOR_ID}")
                        }
                        else {
                            Constants.USER_MENTEE_ID = it.body()!!.menteeId
                            Log.e("AppTest", "MainActivityViewModel/ 현재 로그인 멘티 아이디 : ${Constants.USER_MENTEE_ID}")
                        }
                        isGetUserInfoSuccess.value = true
                    }
                    else{
                        Log.e("AppTest", "MainActivityViewModel/ 유저정보 조회 실패")
                        isGetUserInfoSuccess.value = false
                    }

                    progressBarVisibility_userinfo.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MainActivityViewModel/ 유저정보 조회 오류, ${throwable}")
                    progressBarVisibility_userinfo.value = false
                    isGetUserInfoSuccess.value = false
                }
        )
    }


}