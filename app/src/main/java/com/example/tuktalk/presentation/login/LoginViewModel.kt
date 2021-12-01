package com.example.tuktalk.presentation.login

import android.annotation.SuppressLint
import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.request.UserLoginRequestDto
import com.example.tuktalk.data.remote.dto.response.UserLoginResponseDto
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

                        // 존재하지 않은 이메일인 경우에도 code값 200이므로 처리해줘야 한다
                        if(it.body()?.accessToken == null){
                            Log.e("AppTest", "실패")
                            Log.e("AppTest", "회원가입이 되어있지 않은 사용자 입니다")

                            isLoginSuccess.value = false
                        }
                        else {  // 로그인 성공 시
                            Log.e("AppTest", "성공")
                            Log.e("AppTest", "로그인 성공!")

                            Constants_gitignore.USER_TOKEN = "Bearer " + it.body()!!.accessToken // 토큰 값 저장
                            Constants.USER_NICKNAME = it.body()!!.nickname // 닉네임 저장
                            Constants.USER_PROFILE_IMAGE_COLOR = it.body()!!.profileImageColor // 프로필 랜덤 배경 색상
                            Constants.USER_FIRST_LETTER = it.body()!!.firstLetter // 닉네임 첫 글자
                            Constants.USER_EMAIL = it.body()!!.email // 유저 이메일


                            if(it.body()!!.role.equals("MENTOR"))
                                Constants.USER_MODE = 0  // 멘토
                            else
                                Constants.USER_MODE = 1  // 멘티

                            Log.e("AppTest", "LoginViewModel/ 닉네임 : ${ Constants.USER_NICKNAME}, 유저토큰 : ${Constants_gitignore.USER_TOKEN},\n " +
                                    "프로필 색상 : ${Constants.USER_PROFILE_IMAGE_COLOR}, 닉네임 첫 글자 : ${Constants.USER_FIRST_LETTER},\n " +
                                    "역할 : ${it.body()!!.role} = 유저모드 : ${Constants.USER_MODE},\n" +
                                    " 유저이메일 : ${Constants.USER_EMAIL}")

                            isLoginSuccess.value = true // 로그인 성공
                        }


                    }
                    else{
                        Log.e("AppTest", "LoginViewModel/ 로그인 실패, status code : ${it.code()}")
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