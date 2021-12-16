package com.nemo.tuktalk.presentation.mypage.mentor.mentorService

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.portfolio.GetPortfolioDetailInfoUseCase

class MentorServiceViewModel(
        private val getPortfolioDetailInfoUseCase: GetPortfolioDetailInfoUseCase
): ViewModel() {

    var IsGetMentorPortfolioInfoSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility_info = MutableLiveData<Boolean>()

    var IsPortfolioRegistered = false

    var PF_CREATED_TIME = ""
    var PF_DESCRIPTION = ""
    var PF_PDF_URL = ""

    @SuppressLint("CheckResult")
    fun getMentorPortfolioInfo(){

        ProgressBarVisibility_info.value = true

        getPortfolioDetailInfoUseCase.getPortfolioDetailInfo(Constants_gitignore.USER_TOKEN, Constants.USER_MENTOR_ID).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "MentorServiceViewModel/ 멘토 포폴 조회 성공")
                        if(it.body()!!.pdfTuktalkFile != null){
                            Log.e("AppTest", "MentorServiceViewModel/ 멘토 포폴 존재")
                            IsPortfolioRegistered = true

                            PF_CREATED_TIME = it.body()!!.createdDateTime.substring(0 until 10)  // 포폴 등록일
                            PF_DESCRIPTION = it.body()!!.description
                            PF_PDF_URL = it.body()!!.pdfTuktalkFile

                        }
                        else{
                            Log.e("AppTest", "MentorServiceViewModel/ 멘토 포폴 미등록 상태")
                            IsPortfolioRegistered = false
                        }

                        IsGetMentorPortfolioInfoSuccess.value = true
                    }
                    else{
                        Log.e("AppTest", "MentorServiceViewModel/ 멘토 포폴 조회 실패")
                        IsGetMentorPortfolioInfoSuccess.value = false
                    }

                    ProgressBarVisibility_info.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MentorServiceViewModel/ 멘토 포폴 조회 오류, ${throwable}")
                    IsGetMentorPortfolioInfoSuccess.value = false
                    ProgressBarVisibility_info.value = false
                }
        )
    }



}