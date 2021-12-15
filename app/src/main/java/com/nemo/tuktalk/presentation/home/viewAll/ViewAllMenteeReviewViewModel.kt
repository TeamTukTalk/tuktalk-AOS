package com.nemo.tuktalk.presentation.home.viewAll

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseDto
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseMentee
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseMentor
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem2
import com.nemo.tuktalk.domain.model.home.ViewAllByTask
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.nemo.tuktalk.domain.usecase.home.GetRealTimeReviewListUseCase
import com.nemo.tuktalk.domain.usecase.home.HomeByTaskMentorListUseCase

class ViewAllMenteeReviewViewModel(
        private val getRealTimeReviewListUseCase: GetRealTimeReviewListUseCase
        )  // 홈 에서 멘티 리뷰 와 같은 api!
    : ViewModel() {


    // 실시간 멘티 후기 조회 결과 담을 리스트
    var ViewAll_Review_List = ArrayList<RealTimeReviewRVitem2>()

    var ViewAll_Get_Review_List_Success = MutableLiveData<Boolean>()
    var ViewAll_progressBarVisibility_review = MutableLiveData<Boolean>()

    var EmptyItem_realTimeReview = RealTimeReviewRVitem2(false,1,
            ReviewResponseDto(0, ReviewResponseMentor("", "", ""), 0, "",
                    ReviewResponseMentee(""), "", "", "", ""))

    @SuppressLint("CheckResult")
    fun viewAllGetReviewList(){
        ViewAll_progressBarVisibility_review.value = true

        //리스트 비우고 시작!
        ViewAll_Review_List.clear()
        var page = 1

        getRealTimeReviewListUseCase.getRealTimeReviewList(Constants_gitignore.USER_TOKEN, page).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "ViewAllMenteeReviewViewModel/ 실시간 후기 리스트 조회 성공")

                        if(it.body() != null){

                            if(it.body()!!.reviews.size == 0){
                                Log.e("AppTest", "ViewAllMenteeReviewViewModel/ 실시간 후기 리스트가 비었습니다")
                            }

                            // empty view 하나 추가
                            ViewAll_Review_List.add(EmptyItem_realTimeReview)
                            // 실제 아이템 뷰들 추가
                            it.body()!!.reviews.forEach{
                                var realTimeReviewRVitem2 = RealTimeReviewRVitem2(false, 2, it)
                                ViewAll_Review_List.add(realTimeReviewRVitem2)
                            }

                            ViewAll_Get_Review_List_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "ViewAllMenteeReviewViewModel/ 실시간 후기 리스트 조회 실패")
                            ViewAll_Get_Review_List_Success.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "ViewAllMenteeReviewViewModel/ 실시간 후기 리스트 조회 실패  code:${it.code()}")
                        ViewAll_Get_Review_List_Success.value = false
                    }

                    ViewAll_progressBarVisibility_review.value = false
                },
                {
                    throwable -> Log.e("AppTest", "ViewAllMenteeReviewViewModel/ error ${throwable}")
                    ViewAll_Get_Review_List_Success.value = false
                    ViewAll_progressBarVisibility_review.value = false
                }
        )
    }

}