package com.example.tuktalk.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.domain.usecase.home.HomeTop5MentorListUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class HomeViewModel(
        private val homeTop5MentorListUseCase: HomeTop5MentorListUseCase
): ViewModel() {

    // top5 멘토 리스트 조회 결과 담을 리스트
    var Top5_Mentor_List = ArrayList<Top5MentorResponseDto>()
    var Get_top5_mentorList_Success = MutableLiveData<Boolean>()
    var progressBarVisibility_top5 = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getTop5MentorList(){   // top5 멘토 리스트 가져오기
        progressBarVisibility_top5.value = true
        homeTop5MentorListUseCase.getTop5MentorList(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "HomeViewModel/ top5 멘토 리스트 조회 성공")

                        if(it.body() != null){
                            it.body()!!.forEach{
                                Log.e("AppTest", "닉네임 : ${it.nickname}")
                                Top5_Mentor_List.add(it)
                            }

                            Get_top5_mentorList_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "HomeViewModel/ top5 리스트가 비었습니다")
                        }
                    }
                    else{
                        Log.e("AppTest", "HomeViewModel/ top5 멘토 리스트 조회 실패  code:${it.code()}")
                        Get_top5_mentorList_Success.value = false
                    }

                    progressBarVisibility_top5.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                    Get_top5_mentorList_Success.value = false
                    progressBarVisibility_top5.value = false
                }
        )
    }

}