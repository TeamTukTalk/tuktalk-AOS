package com.example.tuktalk.presentation.login

import android.annotation.SuppressLint
import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.request.UserLoginRequestDto
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class LoginViewModel(
        private val userLoginUseCase: UserLoginUseCase
):ViewModel() {

    var isLoginSuccess = MutableLiveData<Boolean>()
    var progressBarVisibility = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun logIn(userLoginRequestDto: UserLoginRequestDto){
        progressBarVisibility.value = true
        userLoginUseCase.userLogin(userLoginRequestDto).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "로그인 성공!")

                        Constants_gitignore.USER_TOKEN = it.body()!!.accessToken // 토큰 값 저장
                        Constants.USER_NICKNAME = it.body()!!.nickname

                        Log.e("AppTest", "닉네임 : ${ Constants.USER_NICKNAME}, 유저토큰 : ${Constants_gitignore.USER_TOKEN}")

                        isLoginSuccess.value = true // 로그인 성공
                    }
                    else{
                        Log.e("AppTest", "로그인 실패, status code : ${it.code()}")
                        isLoginSuccess.value = false
                    }

                    progressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                    progressBarVisibility.value = false
                    isLoginSuccess.value = false
                }
        )
    }
}