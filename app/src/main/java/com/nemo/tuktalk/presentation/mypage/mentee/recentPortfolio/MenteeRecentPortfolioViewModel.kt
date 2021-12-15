package com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.domain.model.mypage.mentee.wishlist.WishListItem
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.nemo.tuktalk.domain.usecase.mentee.GetRecentHistoryUseCase

class MenteeRecentPortfolioViewModel(
        private val getRecentHistoryUseCase: GetRecentHistoryUseCase
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
                                Log.e("AppTest", "MenteeRecentPortfolioViewModel/ 최근 본 리스트 조회 성공")

                                if (it.body()!!.size == 0) {
                                    Log.e("AppTest", "MenteeRecentPortfolioViewModel/ 결과 리스트 empty")
                                    IsResultEmpty = true
                                } else {
                                    Log.e("AppTest", "MenteeRecentPortfolioViewModel/ 결과 리스트 not empty")
                                    IsResultEmpty = false
                                }

                                // 상단 empty item view 하나 넣기
                                RecentHistoryList.add(RecentHistoryItem(1, emptyMenteeRecentHistoryResponseDto))

                                it.body()!!.forEach {
                                    Log.e("AppTest", "MenteeRecentPortfolioViewModel/ 닉네임 : ${it.mentorNickname}, id : ${it.mentorId}")
                                    RecentHistoryList.add(RecentHistoryItem(2, it))
                                }
                                ////////////////////////////////

                                Is_Get_Recent_History_Success.value = true
                            }
                            else{
                                Log.e("AppTest", "MenteeRecentPortfolioViewModel/ 멘토 프로필이 등록되지 않은 멘토의 포트폴리오를 열람했습니다.")
                                Is_Get_Recent_History_Success.value = false
                            }

                        }
                        else{
                            Log.e("AppTest", "MenteeRecentPortfolioViewModel/ response body == null")
                            Is_Get_Recent_History_Success.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "MenteeRecentPortfolioViewModel/ error, code : ${it.code()}")
                        Is_Get_Recent_History_Success.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MenteeRecentPortfolioViewModel/  error ${throwable}")
                    Is_Get_Recent_History_Success.value = false
                    ProgressBarVisibility.value = false
                }
        )
    }


}