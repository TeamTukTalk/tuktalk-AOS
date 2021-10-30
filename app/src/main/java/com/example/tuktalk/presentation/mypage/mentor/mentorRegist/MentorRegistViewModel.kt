package com.example.tuktalk.presentation.mypage.mentor.mentorRegist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MentorRegistViewModel : ViewModel() {

    var isEmailChecked = MutableLiveData<Boolean>(false)

    fun sendMentorEmail(){

        isEmailChecked.value = true
    }
}