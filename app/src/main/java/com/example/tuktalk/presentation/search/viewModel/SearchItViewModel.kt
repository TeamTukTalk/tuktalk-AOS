package com.example.tuktalk.presentation.search.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.example.tuktalk.domain.model.home.ViewAllByTask
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.example.tuktalk.domain.model.search.SearchDesignMentorList
import com.example.tuktalk.domain.model.search.SearchItMentorList
import com.example.tuktalk.domain.usecase.search.SearchMentorListUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class SearchItViewModel(
        private val searchMentorListUseCase: SearchMentorListUseCase
): ViewModel() {

    // IT/개발 멘토 리스트
    var Search_It_Mentor_List = ArrayList<SearchItMentorList>()

    var IsSearchItMentorListSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    var IsResultEmpty = false

    var emptyItemView = SearchMentorResponseDto(0, "", "", "", "",
        "", "", ArrayList<HashTag>())


    // 검색 api
    @SuppressLint("CheckResult")
    fun searchMentorList(subSpeciality : String, companySize: String, startYear: Int){

        // 고정 query
        var tagMap1 = mutableMapOf("query" to "IT/개발")  // IT/개발 분야이므로 "query" = IT/개발 -> 항상 포함

        var tagMap2 = mutableMapOf("page" to 1)  // "page"는 항상 포함


        if(subSpeciality.length > 0){
            tagMap1.put("subSpecialty", subSpeciality)  // api 문서에 subSpecialty로 되어있음, 우선 그냥 사용하기!
            Log.e("AppTest", "SearchItViewModel/ subSpeciality 포함, value : ${subSpeciality}")
        }
        if(companySize.length > 0){
            tagMap1.put("companySize", companySize)
            Log.e("AppTest", "SearchItViewModel/ companySize 포함, value : ${companySize}")
        }
        if(startYear > 0){
            tagMap2.put("startYear", startYear)
            Log.e("AppTest", "SearchItViewModel/ startYear 포함, value : ${startYear}")
        }

        Log.e("AppTest", "SearchItViewModel/ tag1Map size : ${tagMap1.size}, tag2Map size : ${tagMap2.size}")

        // companySize 에서 현재 '프리랜서' 업데이트x 상태 ->  프리랜서 선택하는 경우는 그냥 없이 진행!!

        ProgressBarVisibility.value = true
        Search_It_Mentor_List.clear()  // 비우고 시작

        searchMentorListUseCase.searchMentorList(Constants_gitignore.USER_TOKEN, tagMap1, tagMap2 ).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "SearchItViewModel/ IT/개발 멘토 리스트 조회 성공")

                        if(it.body() != null){

                            if(it.body()!!.size == 0){  // 통신은 성공했으나 결과가 없는 경우!!
                                Log.e("AppTest","SearchItViewModel/ 검색 결과가 없습니다!")
                                IsResultEmpty = true
                            }
                            else{
                                Log.e("AppTest","SearchItViewModel/ 검색 결과 존재!")
                                IsResultEmpty = false
                            }

                            // 상단 텍스트 아이템 뷰는 결과 상관없이 하나 넣기
                            Search_It_Mentor_List.add(SearchItMentorList(1, emptyItemView))
                            it.body()!!.forEach{
                                Log.e("AppTest", "SearchItViewModel/ 닉네임 : ${it.nickname}  id : ${it.id}")
                                Search_It_Mentor_List.add(SearchItMentorList(2, it))
                            }
                            //////////////////////////////

                            IsSearchItMentorListSuccess.value = true

                        }
                        else{
                            Log.e("AppTest", "SearchItViewModel/ 오류")
                            IsSearchItMentorListSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "SearchItViewModel/ 디자인 리스트 조회 실패  code:${it.code()}")
                        IsSearchItMentorListSuccess.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "SearchItViewModel/ 디자인 리스트 조회 오류, ${throwable}")
                    ProgressBarVisibility.value = false
                    IsSearchItMentorListSuccess.value = false
                }
        )
    }
}