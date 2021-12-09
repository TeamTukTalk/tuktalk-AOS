package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface SearchApi {

    // 탐색 탭 멘토 리스트 검색하기
    @GET("api/search/mentor-profile")
    fun searchMentorList(
            @Header("Authorization")userToken: String,
            @QueryMap tag1 : Map<String, String>,
            @QueryMap tag2 : Map<String, Int>
    ): Single<Response<ArrayList<SearchMentorResponseDto>>>

}