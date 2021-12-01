package com.example.tuktalk.domain.repository

import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import io.reactivex.Single
import retrofit2.Response

interface HomeRepository {

    // top5 멘토 리스트 조회
    fun getTop5MentorList(userToken: String) : Single<Response<ArrayList<Top5MentorResponseDto>>>

}