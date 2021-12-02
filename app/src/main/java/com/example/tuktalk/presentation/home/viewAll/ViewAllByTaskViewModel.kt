package com.example.tuktalk.presentation.home.viewAll

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.example.tuktalk.domain.model.home.ViewAllByTask
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.example.tuktalk.domain.usecase.home.HomeByTaskMentorListUseCase

class ViewAllByTaskViewModel(
        private val viewAllByTaskMentorListUseCase: HomeByTaskMentorListUseCase)  // 홈 에서 직무별과 같은 api 사용!
    : ViewModel() {


    // 직무별 뚝딱 멘토 리스트 조회 결과 담을 리스트트
    var ViewAll_ByTask_Mentor_List = ArrayList<ViewAllByTask>()

    var ViewAll_Get_byTask_mentorList_Success = MutableLiveData<Boolean>()
    var ViewAll_progressBarVisibility_byTask = MutableLiveData<Boolean>()

    var emptyByTaskMentorResponseDto = ByTaskMentorResponseDto(0,"","",
            "","", "", "", ArrayList<HashTag>())

    @SuppressLint("CheckResult")
    fun viewAllGetByTaskMentorList(speciality: String){
        ViewAll_progressBarVisibility_byTask.value = true

        //리스트 비우고 시작!
        ViewAll_ByTask_Mentor_List.clear()

        viewAllByTaskMentorListUseCase.getByTaskMentorList(Constants_gitignore.USER_TOKEN, speciality).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "ViewAllByTaskViewModel/ 직무별 뚝딱 멘토 리스트 조회 성공 speciality:${speciality}")

                        if(it.body() != null){

                            // empty item view 위함!!
                            ViewAll_ByTask_Mentor_List.add(ViewAllByTask(emptyByTaskMentorResponseDto, 1))

                            it.body()!!.forEach{
                                Log.e("AppTest", "닉네임 : ${it.nickname}  id : ${it.id}")
                                ViewAll_ByTask_Mentor_List.add(ViewAllByTask(it, 2))
                            }

                            ViewAll_Get_byTask_mentorList_Success.value = true
                        }
                        else{
                            Log.e("AppTest", "ViewAllByTaskViewModel/ 직무별 리스트가 비었습니다")
                        }
                    }
                    else{
                        Log.e("AppTest", "ViewAllByTaskViewModel/ 직무별 리스트 조회 실패  code:${it.code()}")
                        ViewAll_Get_byTask_mentorList_Success.value = false
                    }

                    ViewAll_progressBarVisibility_byTask.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")
                    ViewAll_Get_byTask_mentorList_Success.value = false
                    ViewAll_progressBarVisibility_byTask.value = false
                }
        )
    }

}