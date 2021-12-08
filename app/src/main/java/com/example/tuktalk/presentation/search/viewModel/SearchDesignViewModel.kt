package com.example.tuktalk.presentation.search.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.domain.model.home.ViewAllByTask
import com.example.tuktalk.domain.usecase.search.SearchMentorListUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase

class SearchDesignViewModel(
        private val searchMentorListUseCase: SearchMentorListUseCase
): ViewModel() {




    // 검색 api
    @SuppressLint("CheckResult")
    fun searchMentorList(subSpeciality : String, companySize: String, startYear: Int){

        var tagMap1 = mutableMapOf("query" to "디자인")  // 디자인 분야이므로 "query" = 디자인 항상 포함
        //tagMap1.put("companySize", "대기업")

        var tagMap2 = mutableMapOf("page" to 1)  // "page"는 항상 포함
        //tagMap2.put("startYear", 5)

        if(subSpeciality.length > 0){
            tagMap1.put("subSpecialty", subSpeciality)  // api 문서에 subSpecialty로 되어있음, 우선 그냥 사용하기!
            Log.e("AppTest", "subSpeciality 포함, value : ${subSpeciality}")
        }
        if(companySize.length > 0){
            tagMap1.put("companySize", companySize)
            Log.e("AppTest", "companySize 포함, value : ${companySize}")
        }
        if(startYear > 0){
            tagMap2.put("startYear", startYear)
            Log.e("AppTest", "startYear 포함, value : ${startYear}")
        }

        Log.e("AppTest", "tag1Map size : ${tagMap1.size}, tag2Map size : ${tagMap2.size}")

        // companySize 에서 현재 '프리랜서' 업데이트x 상태 ->  프리랜서 선택하는 경우는 그냥 없이 진행!!

        searchMentorListUseCase.searchMentorList(Constants_gitignore.USER_TOKEN, tagMap1, tagMap2 ).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "SearchDesignViewModel/ 디자인 멘토 리스트 조회 성공")

                        if(it.body() != null){

                            if(it.body()!!.size == 0){
                                Log.e("AppTest","SearchDesignViewModel/ 검색 결과가 없습니다!")
                            }

                            it.body()!!.forEach{
                                Log.e("AppTest", "닉네임 : ${it.nickname}  id : ${it.id}")

                            }


                        }
                        else{
                            Log.e("AppTest", "SearchDesignViewModel/ 디자인 리스트가 비었습니다")
                        }
                    }
                    else{
                        Log.e("AppTest", "SearchDesignViewModel/ 디자인 리스트 조회 실패  code:${it.code()}")
                    }


                },
                {

                }
        )
    }
}