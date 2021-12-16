package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.request.mentor.RegistPortfolioRequestDto
import com.nemo.tuktalk.domain.usecase.portfolio.RegistPortfolioUseCase
import com.nemo.tuktalk.domain.usecase.portfolio.UploadPdfFileUseCase
import okhttp3.MultipartBody
import java.io.File

class RegistPortfolioViewModel(
        private val uploadPdfFileUseCase: UploadPdfFileUseCase,
        private val registPortfolioUseCase: RegistPortfolioUseCase
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

    var PDF_ID = 0
    var IsPdfUploadSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility_pdf = MutableLiveData<Boolean>()

    // pdf 파일 업로드
    @SuppressLint("CheckResult")
    fun uploadPdfFile(pdfFile : MultipartBody.Part){

        ProgressBarVisibility_pdf.value = true

        uploadPdfFileUseCase.uploadPdfFile(Constants_gitignore.USER_TOKEN, pdfFile).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "RegistPortfolioViewModel/ pdf 업로드 성공")
                        Log.e("AppTest", "RegistPortfolioViewModel/ ${it.body()!!.portfolioId}")
                        PDF_ID = it.body()!!.portfolioId
                        IsPdfUploadSuccess.value = true
                    }
                    else{
                        Log.e("AppTest", "RegistPortfolioViewModel/ pdf 업로드 실패")
                        IsPdfUploadSuccess.value = false
                    }

                    ProgressBarVisibility_pdf.value = false
                },
                {
                    throwable -> Log.e("AppTest", "RegistPortfolioViewModel/ upload pdf error ${throwable}")
                    IsPdfUploadSuccess.value = false
                    ProgressBarVisibility_pdf.value = false
                }
        )
    }

    ////////////////////////////////////////////

    // 포트폴리오 정보 최종 등록하기

    var IsRegistPortfolioSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility_regist_portfolio = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun registPortfolio(){

        ProgressBarVisibility_regist_portfolio.value = true

        var portfolioData = RegistPortfolioRequestDto(DESCRIPTION, PROJECT_COUNT, TOTAL_PAGES, START_YEAR, END_YEAR,
        RECOMMENDATION_TARGET_DESCRIPTION, PDF_ID, ArrayList<Int>())

        registPortfolioUseCase.registPortfolio(Constants_gitignore.USER_TOKEN, portfolioData).subscribe(
                {

                    if(it.code() == 200){
                        if(it.body()!!.id != null){
                            Log.e("AppTest", "RegistPortfolioViewModel/ 포트폴리오 최종 업로드 성공")

                            Log.e("AppTest", "RegistPortfolioViewModel/ id : ${it.body()!!.id}")
                            IsRegistPortfolioSuccess.value = true
                        }
                        else{
                            Log.e("AppTest", "RegistPortfolioViewModel/ 포트폴리오 최종 업로드 실패")
                            IsRegistPortfolioSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "RegistPortfolioViewModel/ 포트폴리오 최종 업로드 실패")
                        IsRegistPortfolioSuccess.value = false
                    }

                    ProgressBarVisibility_regist_portfolio.value = false
                },
                {
                    throwable -> Log.e("AppTest", "RegistPortfolioViewModel/ upload portfolio error ${throwable}")
                    IsRegistPortfolioSuccess.value = false
                    ProgressBarVisibility_regist_portfolio.value = false
                }
        )
    }


}