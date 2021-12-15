package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.request.mentee.WriteReviewRequestDto
import com.nemo.tuktalk.domain.usecase.mentee.WriteReviewUseCase

class WriteReviewViewModel(
        private val writeReviewUseCase: WriteReviewUseCase
): ViewModel() {

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


    ///////////////////

    var IsWriteReviewSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    // 리뷰 작성하기
    @SuppressLint("CheckResult")
    fun writeReview(mentorId: Int){

        var writeReviewDto = WriteReviewRequestDto(mentorId, REVIEW_RATING, REVIEW_CONTENT)

        ProgressBarVisibility.value = true

        writeReviewUseCase.writeReview(Constants_gitignore.USER_TOKEN, writeReviewDto).subscribe(
                {
                    if(it.code() == 200){

                        if(it.body()!!.reviewId != null){
                            Log.e("AppTest", "WriteReviewViewModel/  후기 작성 성공")
                            IsWriteReviewSuccess.value = true
                        }
                        else{
                            Log.e("AppTest", "WriteReviewViewModel/  후기 작성 실패")
                            IsWriteReviewSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "WriteReviewViewModel/  후기 작성 실패")
                        IsWriteReviewSuccess.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "WriteReviewViewModel/  error ${throwable}")
                    IsWriteReviewSuccess.value = false
                    ProgressBarVisibility.value = false
                }
        )

    }



}