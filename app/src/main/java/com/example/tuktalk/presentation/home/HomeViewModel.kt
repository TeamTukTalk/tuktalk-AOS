package com.example.tuktalk.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.domain.usecase.home.HomeByTaskMentorListUseCase
import com.example.tuktalk.domain.usecase.home.HomeTop5MentorListUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class HomeViewModel(
        private val homeTop5MentorListUseCase: HomeTop5MentorListUseCase,
        private val homeByTaskMentorListUseCase: HomeByTaskMentorListUseCase
): ViewModel() {

    // top5 멘토 리스트 조회 결과 담을 리스트
    var Top5_Mentor_List = ArrayList<Top5MentorResponseDto>()

    // 직무별 뚝딱 멘토 리스트 조회 결과 담을 리스트트
   var ByTask_Mentor_List = ArrayList<ByTaskMentorResponseDto>()

    var Get_top5_mentorList_Success = MutableLiveData<Boolean>()
    var progressBarVisibility_top5 = MutableLiveData<Boolean>()

    var Get_byTask_mentorList_Success = MutableLiveData<Boolean>()
    var progressBarVisibility_byTask = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getTop5MentorList(){   // top5 멘토 리스트 가져오기
        progressBarVisibility_top5.value = true

        // 리스트 비우고 시작
        Top5_Mentor_List.clear()

        homeTop5MentorListUseCase.getTop5MentorList(Constants_gitignore.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "HomeViewModel/ top5 멘토 리스트 조회 성공")

                        if(it.body() != null){
                            it.body()!!.forEach{
                                Log.e("AppTest", "닉네임 : ${it.nickname} id : ${it.id}")
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


    @SuppressLint("CheckResult")
    fun getByTaskMentorList(speciality: String){
        progressBarVisibility_byTask.value = true

        //리스트 비우고 시작!
        ByTask_Mentor_List.clear()

        homeByTaskMentorListUseCase.getByTaskMentorList(Constants_gitignore.USER_TOKEN, speciality).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "HomeViewModel/ 직무별 뚝딱 멘토 리스트 조회 성공 speciality:${speciality}")

                        if(it.body() != null){
                            it.body()!!.forEach{
                                Log.e("AppTest", "닉네임 : ${it.nickname}  id : ${it.id}")
                                ByTask_Mentor_List.add(it)
                            }

                            Get_byTask_mentorList_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "HomeViewModel/ 직무별 리스트가 비었습니다")
                        }
                    }
                    else{
                        Log.e("AppTest", "HomeViewModel/ 직무별 리스트 조회 실패  code:${it.code()}")
                        Get_byTask_mentorList_Success.value = false
                    }

                    progressBarVisibility_byTask.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                    Get_byTask_mentorList_Success.value = false
                    progressBarVisibility_byTask.value = false
                }
        )
    }

}