package com.example.tuktalk.domain.repository

import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import io.reactivex.Single
import retrofit2.Response

interface SearchRepository {

    // 멘토 리스트 검색
    fun searchMentorList(userToken: String, tag1 : Map<String, String>, tag2:Map<String, Int>)
    : Single<Response<ArrayList<SearchMentorResponseDto>>>
}