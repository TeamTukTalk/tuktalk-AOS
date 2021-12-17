package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.detailpage

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.portfolio.GetPortfolioDetailInfoUseCase

class PortfolioDetailViewModel(
        private val getPortfolioDetailInfoUseCase: GetPortfolioDetailInfoUseCase
): ViewModel() {

    /// 포트폴리오 상세 정보 ////////////////

    var ProgressBarVisibility_portfolio = MutableLiveData<Boolean>()
    var IsGetPortfolioDetialInfoSuccess = MutableLiveData<Boolean>()

    var PortFolioId = -1
    var PortFolioPdfUrl = ""
    var PortFolio_Description = ""
    var ProjectCount = 0
    var TotalPages = 0
    var StartYear = 0
    var EndYear = 0
    var RecommendationTargetDescription = ""
    var ImageFilesList = ArrayList<String>()

    // 포트폴리오 상세 정보 조회
    @SuppressLint("CheckResult")
    fun getPortfolioDetailInfo(mentorId: Int){

        Log.e("AppTest", "PortfolioDetailViewModel/ 포트폴리오 상세조회에 사용한 mentorId : ${mentorId}")

        ProgressBarVisibility_portfolio.value = true

        getPortfolioDetailInfoUseCase.getPortfolioDetailInfo(Constants_gitignore.USER_TOKEN, mentorId).subscribe(
                {
                    if(it.code() == 200){
                        if(it.body() != null){
                            Log.e("AppTest", "PortfolioDetailViewModel/ not empty response body")

                            if(it.body()!!.pdfTuktalkFile != null){
                                Log.e("AppTest", "PortfolioDetailViewModel/ 포트폴리오 상세 정보 조회 성공, pdf url not null")
                                Log.e("AppTest", "PortfolioDetailViewModel/ portfolioId : ${it.body()!!.portfolioId} ..." )
                                Log.e("AppTest", "PortfolioDetailViewModel/ pdf url : ${it.body()!!.pdfTuktalkFile} ..." )

                                PortFolioId = it.body()!!.portfolioId
                                PortFolioPdfUrl = it.body()!!.pdfTuktalkFile
                                PortFolio_Description = it.body()!!.description
                                ProjectCount = it.body()!!.projectCount
                                TotalPages = it.body()!!.totalPages
                                StartYear = it.body()!!.startYear
                                EndYear = it.body()!!.endYear
                                RecommendationTargetDescription = it.body()!!.recommendationTargetDescription
                                ImageFilesList = it.body()!!.imageFiles

                                Log.e("AppTest", "PortfolioDetailViewModel/ 포트폴리오 상세 조회 결과\n" +
                                        "PortFolioId : ${PortFolioId}\n" +
                                        "PortFolioPdfUrl : ${PortFolioPdfUrl}\n" +
                                        "PortFolio_Description : ${PortFolio_Description}\n" +
                                        "ProjectCount : ${ProjectCount}\n" +
                                        "TotalPages : ${TotalPages}\n" +
                                        "StartYear : ${StartYear}\n" +
                                        "EndYear : ${EndYear}\n" +
                                        "RecommendationTargetDescription : ${RecommendationTargetDescription}\n" +
                                        "ImageFilesList : ${ImageFilesList}")


                                IsGetPortfolioDetialInfoSuccess.value = true
                            }
                            else {
                                Log.e("AppTest", "PortfolioDetailViewModel/ 현재 멘토 포트폴리오 미등록 상태, pdf url = null")
                                IsGetPortfolioDetialInfoSuccess.value = false
                            }
                        }
                        else{
                            Log.e("AppTest", "PortfolioDetailViewModel/ empty response body")
                            IsGetPortfolioDetialInfoSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "PortfolioDetailViewModel/ 포트폴리오 상세 정보 조회 실패")
                        IsGetPortfolioDetialInfoSuccess.value = false
                    }

                    ProgressBarVisibility_portfolio.value = false


                },
                {
                    throwable -> Log.e("AppTest", "PortfolioDetailViewModel/ throwable : ${throwable},  포트폴리오 상세 정보 조회 오류")
                    ProgressBarVisibility_portfolio.value = false
                    IsGetPortfolioDetialInfoSuccess.value = false
                }
        )
    }

}