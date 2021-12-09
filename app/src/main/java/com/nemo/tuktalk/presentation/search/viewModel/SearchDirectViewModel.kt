package com.nemo.tuktalk.presentation.search.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.nemo.tuktalk.domain.usecase.search.SearchMentorListUseCase

class SearchDirectViewModel(
        private val searchMentorListUseCase: SearchMentorListUseCase
): ViewModel() {

    // IT/개발 멘토 리스트
    var Search_Direct_Mentor_List = ArrayList<SearchMentorResponseDto>()

    var IsSearchDirectMentorListSuccess = MutableLiveData<Boolean>()
    var ProgressBarVisibility = MutableLiveData<Boolean>()

    var IsResultEmpty = false


    // 검색 api
    @SuppressLint("CheckResult")
    fun searchMentorList(query : String){

        // 고정 query , 직접검색의 경우  query만 사용!!
        var tagMap1 = mutableMapOf("query" to query)

        var tagMap2 = mutableMapOf("page" to 1)  // "page"는 항상 포함

        Log.e("AppTest", "SearchDirectViewModel/ tag1Map size : ${tagMap1.size}, tag2Map size : ${tagMap2.size}")

        ProgressBarVisibility.value = true
        Search_Direct_Mentor_List.clear()  // 비우고 시작

        searchMentorListUseCase.searchMentorList(Constants_gitignore.USER_TOKEN, tagMap1, tagMap2 ).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "SearchDirectViewModel/ 직접검색 멘토 리스트 조회 성공")

                        if(it.body() != null){

                            if(it.body()!!.size == 0){  // 통신은 성공했으나 결과가 없는 경우!!
                                Log.e("AppTest","SearchDirectViewModel/ 검색 결과가 없습니다!")
                                IsResultEmpty = true
                            }
                            else{
                                Log.e("AppTest","SearchDirectViewModel/ 검색 결과 존재!")
                                IsResultEmpty = false
                            }


                            it.body()!!.forEach{
                                Log.e("AppTest", "SearchDirectViewModel/ 닉네임 : ${it.nickname}  id : ${it.id}")
                                Search_Direct_Mentor_List.add(it)
                            }
                            //////////////////////////////

                            IsSearchDirectMentorListSuccess.value = true

                        }
                        else{
                            Log.e("AppTest", "SearchDirectViewModel/ 오류")
                            IsSearchDirectMentorListSuccess.value = false
                        }
                    }
                    else{
                        Log.e("AppTest", "SearchDirectViewModel/ 직접검색 리스트 조회 실패  code:${it.code()}")
                        IsSearchDirectMentorListSuccess.value = false
                    }

                    ProgressBarVisibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "SearchDirectViewModel/ 직접검색 리스트 조회 오류, ${throwable}")
                    ProgressBarVisibility.value = false
                    IsSearchDirectMentorListSuccess.value = false
                }
        )
    }
}