package com.example.tuktalk.data.remote

import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApi {

    // top5 멘토 리스트 가져오기
    @GET("api/mentors/top5")
    fun getTop5Mentor(
            @Header("Authorization")userToken: String
    ): Single<Response<ArrayList<Top5MentorResponseDto>>>
}