package com.example.tuktalk.presentation.mypage.mentor.mentorProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MentorProfileViewModel: ViewModel() {
    // step1
    var isIntroduceSimpleFilled = false
    var isIntroduceDetailFilled = false
    var step1Checked = MutableLiveData<Boolean>()

    var SIMPLE_INTRODUCTION = ""
    var DETAILED_INTRODUCTION = ""


    fun fillSimpleIntroduce(flag : Boolean){
        isIntroduceSimpleFilled = flag
        step1Check()
    }
    fun fillDeatailedIntroduce(flag: Boolean){
        isIntroduceDetailFilled = flag
        step1Check()
    }
    fun step1Check(){
        step1Checked.value = isIntroduceSimpleFilled && isIntroduceDetailFilled
    }
    ////////////




}