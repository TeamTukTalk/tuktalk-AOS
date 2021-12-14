package com.nemo.tuktalk.presentation.mypage.mentee.wishlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.domain.model.home.ViewAllByTask
import com.nemo.tuktalk.domain.model.mypage.mentee.wishlist.WishListItem
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.nemo.tuktalk.domain.usecase.mentee.GetWishListUseCase

class MenteeWishListViewModel(
        private val getWishListUseCase: GetWishListUseCase
): ViewModel() {

    // 찜 멘토 리스트
    var MenteeWishList = ArrayList<WishListItem>()

    var Is_Get_Wish_List_Success = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    var emptyMenteeWishListResponseDto = MenteeWishListResponseDto(0,0,"","",
            "","", "", "", ArrayList<HashTag>())

    var IsResultEmpty = false


    @SuppressLint("CheckResult")
    fun getMenteeWishList(){
        ProgressBarVisibility.value = true
        MenteeWishList.clear() // 비우고 시작

       getWishListUseCase.getMenteeWishList(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        if(it.body() != null){
                            Log.e("AppTest", "MenteeWishListViewModel/ 찜목록 리스트 조회 성공")

                            if(it.body()!!.size == 0){
                                Log.e("AppTest", "MenteeWishListViewModel/ 결과 리스트 empty")
                                IsResultEmpty = true
                            }
                            else{
                                Log.e("AppTest", "MenteeWishListViewModel/ 결과 리스트 not empty")
                                IsResultEmpty = false
                            }

                            // 상단 empty item view 하나 넣기
                            MenteeWishList.add(WishListItem(1, emptyMenteeWishListResponseDto))

                            it.body()!!.forEach{
                                Log.e("AppTest", "MenteeWishListViewModel/ 닉네임 : ${it.nickname}  id : ${it.mentorId}")
                                MenteeWishList.add(WishListItem(2, it))
                            }
                            ////////////////////////////////

                            Is_Get_Wish_List_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "MenteeWishListViewModel/ response body == null")
                            Is_Get_Wish_List_Success.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "MenteeWishListViewModel/ error, code : ${it.code()}")
                        Is_Get_Wish_List_Success.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "MenteeWishListViewModel/  error ${throwable}")
                    Is_Get_Wish_List_Success.value = false
                    ProgressBarVisibility.value = false
                }
        )
    }
}