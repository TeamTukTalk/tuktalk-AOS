package com.example.tuktalk.presentation.mypage.mentor.mentorInfo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.domain.model.mypage.mentor.info.SubSpecialty
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.CareerInput
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.example.tuktalk.domain.usecase.mentor.info.GetMentorDetailInfoUseCase
import com.example.tuktalk.domain.usecase.portfolio.GetPortfolioDetailInfoUseCase

class MentorInfoViewModel(
    private val getMentorDetailInfoUseCase: GetMentorDetailInfoUseCase,
    private val getPortfolioDetailInfoUseCase: GetPortfolioDetailInfoUseCase
): ViewModel() {

    var CURRENT_MENTOR_ID = 0

   ///////////// 멘토 상세정보 ////////////////
    var ProgressBarVisibility_info = MutableLiveData<Boolean>()
    var IsGetMentorDetialInfoSuccess = MutableLiveData<Boolean>()

    // 멘토 상세 정보보
   var MentorId = -1
    var ProfileImageUrl = ""
    var ProfileImageColor = ""
    var FirstLetter = ""
    var Nickname = ""
    var SimpleIntroduction = ""
    var DetailedIntroduction = ""
    var CompanyName = ""
    var Specialty = ""
    var SubSpecialties = ArrayList<SubSpecialty>()
    var Position = ""
    var Career = CareerInput(0,0)
    var CareerDescription = ""
    var HashTags = ArrayList<HashTag>()


    // 멘토 상제정보 조회
    @SuppressLint("CheckResult")
    fun getMentorDetailInfo(mentorId: Int){

        ProgressBarVisibility_info.value = true

        getMentorDetailInfoUseCase.getMentorDetailInfo(Constants_gitignore.USER_TOKEN, mentorId).subscribe(
            {

                if(it.code() == 200){
                    Log.e("AppTest", "MentorInfoViewModel/ 멘토 상세 정보 조회 성공")

                    MentorId = it.body()!!.mentorId
                    ProfileImageUrl = it.body()!!.profileImageUrl
                    ProfileImageColor = it.body()!!.profileImageColor
                    FirstLetter = it.body()!!.firstLetter
                    Nickname = it.body()!!.nickname
                    SimpleIntroduction = it.body()!!.simpleIntroduction
                    DetailedIntroduction = it.body()!!.detailedIntroduction
                    CompanyName = it.body()!!.companyName
                    Specialty = it.body()!!.specialty
                    SubSpecialties = it.body()!!.subSpecialties
                    Position = it.body()!!.position
                    Career = it.body()!!.career
                    CareerDescription = it.body()!!.careerDescription
                    HashTags = it.body()!!.hashTags
                    Log.e("AppTest", "MentorInfoViewModel/ 상세 정보 조회 결과\n" +
                            "MentorID : ${MentorId}\n" +
                            "ProfileImageUrl : ${ProfileImageUrl}\n" +
                            "ProfileImageColor : ${ProfileImageColor}\n" +
                            "FirstLetter : ${FirstLetter}\n" +
                            "Nickname : ${Nickname}\n" +
                            "SimpleIntroduction : ${SimpleIntroduction}\n" +
                            "DetailedIntroduction : ${DetailedIntroduction}\n" +
                            "CompanyName : ${CompanyName}\n" +
                            "Specialty : ${Specialty}\n" +
                            "SubSpecialties : ${SubSpecialties}\n" +
                            "Position : ${Position}\n" +
                            "Career : ${Career}\n" +
                            "CareerDescription : ${CareerDescription}\n" +
                            "HashTags : ${HashTags}")


                    IsGetMentorDetialInfoSuccess.value = true
                }
                else{
                    Log.e("AppTest", "MentorInfoViewModel/ 멘토 상세 정보 조회 실패")
                    IsGetMentorDetialInfoSuccess.value = false
                }

                ProgressBarVisibility_info.value = false
            },
            {
                throwable -> Log.e("AppTest", "MentorInfoViewModel/ throwable : ${throwable},  멘토 상세 정보 조회 오류")
                ProgressBarVisibility_info.value = false
                IsGetMentorDetialInfoSuccess.value = false
            }
        )
    }

    //////////////////////////////////

    /// 포트폴리오 상세 정보 ////////////////

    var ProgressBarVisibility_portfolio = MutableLiveData<Boolean>()
    var IsGetPortfolioDetialInfoSuccess = MutableLiveData<Boolean>()

    var IsPortfolioUnregistered = false

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

        Log.e("AppTest", "MentorInfoViewModel/ 포트폴리오 상세조회에 사용한 mentorId : ${mentorId}")

        ProgressBarVisibility_portfolio.value = true

        getPortfolioDetailInfoUseCase.getPortfolioDetailInfo(Constants_gitignore.USER_TOKEN, mentorId).subscribe(
            {
                if(it.code() == 200){
                    if(it.body() != null){
                        Log.e("AppTest", "MentorInfoViewModel/ not empty response body")

                        if(it.body()!!.pdfTuktalkFile != null){
                            Log.e("AppTest", "MentorInfoViewModel/ 포트폴리오 상세 정보 조회 성공, pdf url not null")
                            Log.e("AppTest", "MentorInfoViewModel/ portfolioId : ${it.body()!!.portfolioId} ..." )
                            Log.e("AppTest", "MentorInfoViewModel/ pdf url : ${it.body()!!.pdfTuktalkFile} ..." )

                            PortFolioId = it.body()!!.portfolioId
                            PortFolioPdfUrl = it.body()!!.pdfTuktalkFile
                            PortFolio_Description = it.body()!!.description
                            ProjectCount = it.body()!!.projectCount
                            TotalPages = it.body()!!.totalPages
                            StartYear = it.body()!!.startYear
                            EndYear = it.body()!!.endYear
                            RecommendationTargetDescription = it.body()!!.recommendationTargetDescription
                            ImageFilesList = it.body()!!.imageFiles

                            Log.e("AppTest", "MentorInfoViewModel/ 포트폴리오 상세 조회 결과\n" +
                                    "PortFolioId : ${PortFolioId}\n" +
                                    "PortFolioPdfUrl : ${PortFolioPdfUrl}\n" +
                                    "PortFolio_Description : ${PortFolio_Description}\n" +
                                    "ProjectCount : ${ProjectCount}\n" +
                                    "TotalPages : ${TotalPages}\n" +
                                    "StartYear : ${StartYear}\n" +
                                    "EndYear : ${EndYear}\n" +
                                    "RecommendationTargetDescription : ${RecommendationTargetDescription}\n" +
                                    "ImageFilesList : ${ImageFilesList}")

                            IsPortfolioUnregistered = false
                            IsGetPortfolioDetialInfoSuccess.value = true
                        }
                        else {
                            Log.e("AppTest", "MentorInfoViewModel/ 현재 멘토 포트폴리오 미등록 상태, pdf url = null")

                            IsPortfolioUnregistered = true  // 통신은 성공했지만 현재 멘토 포폴 미등록 상태임
                        }
                    }
                    else{
                        Log.e("AppTest", "MentorInfoViewModel/ empty response body")
                        IsGetPortfolioDetialInfoSuccess.value = false
                    }
                }
                else{
                    Log.e("AppTest", "MentorInfoViewModel/ 포트폴리오 상세 정보 조회 실패")
                    IsGetPortfolioDetialInfoSuccess.value = false
                }

                ProgressBarVisibility_portfolio.value = false


            },
            {
                throwable -> Log.e("AppTest", "MentorInfoViewModel/ throwable : ${throwable},  포트폴리오 상세 정보 조회 오류")
                ProgressBarVisibility_portfolio.value = false
                IsGetPortfolioDetialInfoSuccess.value = false
            }
        )
    }
}