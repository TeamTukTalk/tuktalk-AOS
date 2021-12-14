package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.portfolio.UploadPdfFileUseCase

class RegistPortfolioViewModel(
        private val uploadPdfFileUseCase: UploadPdfFileUseCase
): ViewModel() {  // 포트폴리오 등록


    // step1
    var DESCRIPTION = ""
    var Step1Checked = MutableLiveData<Boolean>()

    fun fillDescription(userInput : String, flag: Boolean){
        Step1Checked.value = flag
        DESCRIPTION = userInput
    }


    ////////////////////////////////////////////////////

    // step2
    var PROJECT_COUNT = 0
    var TOTAL_PAGES = 0
    var START_YEAR = 0
    var END_YEAR = 0

    var Step2Checked = MutableLiveData<Boolean>()

    fun fillProjectCount(count : Int){
        PROJECT_COUNT = count
        step2Check()
    }

    fun fillPageCount(count : Int){
        TOTAL_PAGES = count
        step2Check()
    }

    fun fillStartYear(year : Int){
        START_YEAR = year
        step2Check()
    }

    fun fillEndYear(year : Int){
        END_YEAR = year
        step2Check()
    }

    fun step2Check(){
        Step2Checked.value = (PROJECT_COUNT > 0) && (TOTAL_PAGES > 0) && (END_YEAR >= START_YEAR) && (START_YEAR > 0)
    }


    ///////////////////////////////////
    // step3
    var RECOMMENDATION_TARGET_DESCRIPTION = ""
    var Step3Checked = MutableLiveData<Boolean>()

    fun fillRecommendationTargetDescription(userInput : String, flag: Boolean){
        Step3Checked.value = flag
        RECOMMENDATION_TARGET_DESCRIPTION = userInput
    }


    //////////////////////////////////////////

    // step4

    @SuppressLint("CheckResult")
    fun uploadPdfFile(encodedPdf : String){

        uploadPdfFileUseCase.uploadPdfFile(Constants_gitignore.USER_TOKEN, encodedPdf).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "RegistPortfolioViewModel/ 업로드 성공")
                    }
                    else{
                        Log.e("AppTest", "RegistPortfolioViewModel/ 업로드 실패")
                    }
                },
                {
                    throwable -> Log.e("AppTest", "RegistPortfolioViewModel/ upload pdf error ${throwable}")
                }
        )
    }

}