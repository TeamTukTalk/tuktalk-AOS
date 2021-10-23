package com.example.tuktalk.presentation.signup.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoRegistViewModel: ViewModel() {
    // 5가지 사항 체크하는 Boolean data 담는 list 형태의 livedata 선언하기!!

    // 1.닉네임  2.아이디  3.비밀번호  4.비밀번호 확인  5.약관 동의

    var isAllInfoEnteredCorrectly = MutableLiveData<Boolean>()
    var isIdAllCorrect = MutableLiveData<Boolean>()
    var isTermsCheck = MutableLiveData<Boolean>(false)

    var isSignUpSuccess = MutableLiveData<Boolean>(false)

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
    fun checkIdDuplicate(){
        // 중복아니면 - 지금은 중복 무조건 아닌걸로 해두기
        infoCorrectCheck(4,true)
        isIdAllCorrect.value = true

        // 중복이면
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

    // 가입 완료시 기입 정보 서버에 전달
    fun signUpClick(){

        // 성공 시
        isSignUpSuccess.value = true
    }

}