package com.nemo.tuktalk.presentation.mypage.mentee.managereview

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDtoList
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseMentee
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseMentor
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.domain.model.mypage.mentee.reviewlist.ReviewListRvItem
import com.nemo.tuktalk.domain.usecase.mentee.GetRecentHistoryUseCase
import com.nemo.tuktalk.domain.usecase.mentee.GetReviewListUseCase

class MenteeManageReviewViewModel(
        private val getRecentHistoryUseCase: GetRecentHistoryUseCase,
        private val getReviewListUseCase: GetReviewListUseCase
): ViewModel() {

    // 최근 본 포트폴리오 리스트
    var RecentHistoryList = ArrayList<RecentHistoryItem>()

    var Is_Get_Recent_History_Success = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    var emptyMenteeRecentHistoryResponseDto = MenteeRecentHistoryResponseDto(0,"","","","")

    var IsResultEmpty = false


    @SuppressLint("CheckResult")
    fun getRecentHistory(){
        ProgressBarVisibility.value = true
        RecentHistoryList.clear() // 비우고 시작

        getRecentHistoryUseCase.getRecentHistory(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        if(it.body() != null) {
                            if (it.body() is ArrayList<MenteeRecentHistoryResponseDto>){
                                    Log.e("AppTest", "MenteeManageReviewViewModel/ 최근 본 리스트 조회 성공")

                                if (it.body()!!.size == 0) {
                                    Log.e("AppTest", "MenteeManageReviewViewModel/ 결과 리스트 empty")
                                    IsResultEmpty = true
                                } else {
                                    Log.e("AppTest", "MenteeManageReviewViewModel/ 결과 리스트 not empty")
                                    IsResultEmpty = false
                                }

                                // 상단 empty item view 하나 넣기
                                RecentHistoryList.add(RecentHistoryItem(1, emptyMenteeRecentHistoryResponseDto))

                                it.body()!!.forEach {
                                    Log.e("AppTest", "MenteeManageReviewViewModel/ 닉네임 : ${it.mentorNickname}, id : ${it.mentorId}")
                                    RecentHistoryList.add(RecentHistoryItem(2, it))
                                }
                                ////////////////////////////////

                                Is_Get_Recent_History_Success.value = true
                            }
                            else{
                                Log.e("AppTest", "MenteeManageReviewViewModel/ 멘토 프로필이 등록되지 않은 멘토의 포트폴리오를 열람했습니다.")
                                Is_Get_Recent_History_Success.value = false
                            }
                        }
                        else{
                            Log.e("AppTest", "MenteeManageReviewViewModel/ response body == null")
                            Is_Get_Recent_History_Success.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "MenteeManageReviewViewModel/ error, code : ${it.code()}")
                        Is_Get_Recent_History_Success.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MenteeManageReviewViewModel/ recent list error ${throwable}")
                    Is_Get_Recent_History_Success.value = false
                    ProgressBarVisibility.value = false
                }
        )
    }

    ///////////////////////////////////////////////////////////////////////////////

    // 멘티 작성 후기 리스트 조회
    var MenteeReviewList = ArrayList<ReviewListRvItem>()

    var Is_Get_Review_List_Success = MutableLiveData<Boolean>()
    var ProgressBarVisibility_review = MutableLiveData<Boolean>()

    var emptyMenteeReviewListResponseDto = MenteeReviewListResponseDto(0, MenteeReviewListResponseMentor("","","")
            ,0,"", MenteeReviewListResponseMentee(""), "", "",
    "", "")

    var IsResultEmpty_review = false


    @SuppressLint("CheckResult")
    fun getMenteeReivewList(){

        MenteeReviewList.clear()
        ProgressBarVisibility_review.value = true

        getReviewListUseCase.getReviewList(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        if(it.body() is MenteeReviewListResponseDtoList){
                            Log.e("AppTest", "MenteeManageReviewViewModel/ 멘티 후기 리스트 조회 성공")

                            if(it.body()!!.reviews.size == 0){
                                Log.e("AppTest", "MenteeManageReviewViewModel/ 멘티 후기 리스트 empty")
                                IsResultEmpty_review = true
                            }
                            else{
                                Log.e("AppTest", "MenteeManageReviewViewModel/ 멘티 후기 리스트 not empty")
                                IsResultEmpty_review = false
                            }

                            // 상단 empty item view 하나 넣기
                            MenteeReviewList.add(ReviewListRvItem(1, emptyMenteeReviewListResponseDto))
                            // 아이템뷰 리스트 넣기
                            it.body()!!.reviews.forEach {
                                Log.e("AppTest", "MenteeManageReviewViewModel/ 멘토닉네임 : ${it.mentor.mentorNickname}")
                                MenteeReviewList.add(ReviewListRvItem(2, it))
                            }


                            Is_Get_Review_List_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "MenteeManageReviewViewModel/ 멘티 후기 리스트 조회 실패")
                            Is_Get_Review_List_Success.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "MenteeManageReviewViewModel/ 멘티 후기 리스트 조회 실패")
                        Is_Get_Review_List_Success.value = false
                    }

                    ProgressBarVisibility_review.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MenteeManageReviewViewModel/  review list error ${throwable}")
                    Is_Get_Review_List_Success.value = false
                    ProgressBarVisibility_review.value = false
                }
        )
    }


}