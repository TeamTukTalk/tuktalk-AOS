package com.example.tuktalk.presentation.signup.info

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.example.tuktalk.domain.usecase.user.UserEmailCheckUseCase
import com.example.tuktalk.domain.usecase.user.UserSignUpUseCase

class InfoRegistViewModel(
        private val userEmailCheckResultUseCase: UserEmailCheckUseCase,
        private val userSignUpUseCase: UserSignUpUseCase
): ViewModel() {
    // 5가지 사항 체크하는 Boolean data 담는 list 형태의 livedata 선언하기!!

    // 1.닉네임  2.아이디  3.비밀번호  4.비밀번호 확인  5.약관 동의

    var isAllInfoEnteredCorrectly = MutableLiveData<Boolean>()
    var isIdAllCorrect = MutableLiveData<Boolean>()
    var isTermsCheck = MutableLiveData<Boolean>(false)

    var isSignUpSuccess = MutableLiveData<Boolean>(false)
    var progressBarVisibility = MutableLiveData<Boolean>()

    private var IsInfoCorrect = Array<Boolean>(6) { false }

    var flag1 = false




    fun infoCorrectCheck(index:Int, flag: Boolean){
        IsInfoCorrect[index] = flag

        for(i in 0..5){
            if(IsInfoCorrect[i] == false){
                isAllInfoEnteredCorrectly.value = false
                return
            }
        }
        // 모두 true 라면 5가지 사항들이 모두 정상적인 값 -> 가입완료 버튼 활성화시키기!!
        isAllInfoEnteredCorrectly.value = true
    }


    // 아이디 중복체크 / 추후 usecase 주입받고 네트워크 통신까지 하도록 구현하기
    @SuppressLint("CheckResult")
    fun checkIdDuplicate(email : String){
        progressBarVisibility.value = true
        Log.e("AppTest", "중복 검사 할 이메일 : ${email}")
        // 통신 후 중복인지 아닌지 결과에 따라 분기해주기!!!
        userEmailCheckResultUseCase.getUserEmailCheckResult(email).subscribe(
                {
                    var isExist = it.isEmailExist
                    if(isExist){
                        Log.e("AppTest", "아이디 중복")
                        infoCorrectCheck(4, false)
                        isIdAllCorrect.value = false
                    }
                    else{
                        Log.e("AppTest", "아이디 중복x  사용가능!")
                        // 중복아니면
                        infoCorrectCheck(4,true)
                        isIdAllCorrect.value = true
                    }
                    progressBarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","이메일 중복 체크 api 오류")

                    progressBarVisibility.value = false
                }
        )


    }

    fun termsCheck(){
        isTermsCheck.value = isTermsCheck.value!!.not()
        if(isTermsCheck.value == true){
            infoCorrectCheck(5,true)
        }
        else{
            infoCorrectCheck(5,false)
        }
    }



    // 가입 완료 클릭시 기입 정보 서버에 전달
    @SuppressLint("CheckResult")
    fun signUpClick(userSignUpRequestDto: UserSignUpRequestDto){
        progressBarVisibility.value = true
        
        // 입력 정보들 넘겨주는 통신 후 결과에 따라 분기하기!!!
        userSignUpUseCase.userSignUp(userSignUpRequestDto).subscribe(
                {
                    progressBarVisibility.value = false
                    Log.e("AppTest", "user signup result status code : ${it.code()}")
                    if(it.code() == 201){
                        Constants_gitignore.USER_TOKEN = "Bearer " + it.body()!!.accessToken
                        Constants.USER_NICKNAME = it.body()!!.nickname // 닉네임 저장
                        Constants.USER_PROFILE_IMAGE_COLOR = it.body()!!.profileImageColor // 프로필 랜덤 배경 색상
                        Constants.USER_FIRST_LETTER = it.body()!!.firstLetter // 닉네임 첫 글자
                        Constants.USER_EMAIL = it.body()!!.email // 유저 이메일

                        if(it.body()!!.role.equals("MENTOR"))
                            Constants.USER_MODE = 0  // 멘토
                        else
                            Constants.USER_MODE = 1  // 멘티

                        Log.e("AppTest", "InfoRegistViewModel/ 회원가입 성공!" +
                                "usertoken : ${Constants_gitignore.USER_TOKEN}\n" +
                                "nickname : ${Constants.USER_NICKNAME}" +
                                "  profilecolor : ${Constants.USER_PROFILE_IMAGE_COLOR}  firstletter : ${Constants.USER_FIRST_LETTER},\n " +
                                "역할 : ${it.body()!!.role} = 유저모드 : ${Constants.USER_MODE}, \n" +
                                "유저이메일 : ${Constants.USER_EMAIL}")

                        isSignUpSuccess.value = true
                    }
                    else{
                        Log.e("AppTest", "회원가입 실패  status code : ${it.code()}")
                    }
                },
                {
                    throwable -> Log.e("AppTest", "sign up error ${throwable}")
                    progressBarVisibility.value = false
                }
        )

    }

}