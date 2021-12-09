package com.nemo.tuktalk.presentation.search.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.nemo.tuktalk.domain.model.search.SearchDesignMentorList
import com.nemo.tuktalk.domain.usecase.search.SearchMentorListUseCase

class SearchDesignViewModel(
        private val searchMentorListUseCase: SearchMentorListUseCase
): ViewModel() {

    // 직무별 뚝딱 멘토 리스트 조회 결과 담을 리스트트
    var Search_Design_Mentor_List = ArrayList<SearchDesignMentorList>()

    var IsSearchDesignMentorListSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    var IsResultEmpty = false

    var emptyItemView = SearchMentorResponseDto(0, "", "", "", "",
        "", "", ArrayList<HashTag>())


    // 검색 api
    @SuppressLint("CheckResult")
    fun searchMentorList(subSpeciality : String, companySize: String, startYear: Int){

        // 고정 query
        var tagMap1 = mutableMapOf("query" to "디자인")  // 디자인 분야이므로 "query" = 디자인 -> 항상 포함
        //tagMap1.put("companySize", "대기업")

        var tagMap2 = mutableMapOf("page" to 1)  // "page"는 항상 포함

        if(subSpeciality.length > 0){
            tagMap1.put("subSpecialty", subSpeciality)  // api 문서에 subSpecialty로 되어있음, 우선 그냥 사용하기!
            Log.e("AppTest", "SearchDesignViewModel/ subSpeciality 포함, value : ${subSpeciality}")
        }
        if(companySize.length > 0){
            tagMap1.put("companySize", companySize)
            Log.e("AppTest", "SearchDesignViewModel/ companySize 포함, value : ${companySize}")
        }
        if(startYear > 0){
            tagMap2.put("startYear", startYear)
            Log.e("AppTest", "SearchDesignViewModel/ startYear 포함, value : ${startYear}")
        }

        Log.e("AppTest", "SearchDesignViewModel/ tag1Map size : ${tagMap1.size}, tag2Map size : ${tagMap2.size}")

        // companySize 에서 현재 '프리랜서' 업데이트x 상태 ->  프리랜서 선택하는 경우는 그냥 없이 진행!!

        ProgressBarVisibility.value = true
        Search_Design_Mentor_List.clear()  // 비우고 시작

        searchMentorListUseCase.searchMentorList(Constants_gitignore.USER_TOKEN, tagMap1, tagMap2 ).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "SearchDesignViewModel/ 디자인 멘토 리스트 조회 성공")

                        if(it.body() != null){

                            if(it.body()!!.size == 0){
                                Log.e("AppTest","SearchDesignViewModel/ 검색 결과가 없습니다!")
                                IsResultEmpty = true
                            }
                            else{
                                Log.e("AppTest","SearchDesignViewModel/ 검색 결과 존재!")
                                IsResultEmpty = false
                            }

                            // 상단 텍스트 아이템 뷰는 결과 상관없이 하나 넣기
                            Search_Design_Mentor_List.add(SearchDesignMentorList(1, emptyItemView))
                            it.body()!!.forEach{
                                Log.e("AppTest", "SearchDesignViewModel/ 닉네임 : ${it.nickname}  id : ${it.id}")
                                Search_Design_Mentor_List.add(SearchDesignMentorList(2, it))
                            }
                            //////////////////////////////

                            IsSearchDesignMentorListSuccess.value = true

                        }
                        else{
                            Log.e("AppTest", "SearchDesignViewModel/ 오류")
                            IsSearchDesignMentorListSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "SearchDesignViewModel/ 디자인 리스트 조회 실패  code:${it.code()}")
                        IsSearchDesignMentorListSuccess.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "SearchDesignViewModel/ 디자인 리스트 조회 오류, ${throwable}")
                    ProgressBarVisibility.value = false
                    IsSearchDesignMentorListSuccess.value = false
                }
        )
    }
}