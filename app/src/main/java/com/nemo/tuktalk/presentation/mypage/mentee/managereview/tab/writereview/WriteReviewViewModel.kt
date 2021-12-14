package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteReviewViewModel: ViewModel() {

    var isRatingSelected = false
    var isReviewNotEmpty = false
    var btnActivate = MutableLiveData<Boolean>()

    var REVIEW_CONTENT = ""
    var REVIEW_RATING = 0


    fun selectRating(flag: Boolean){
        isRatingSelected = flag
        checkInput()
    }

    fun inputReview(flag: Boolean){
        isReviewNotEmpty = flag
        checkInput()
    }

    fun checkInput(){
        btnActivate.value = isRatingSelected && isReviewNotEmpty
    }





}