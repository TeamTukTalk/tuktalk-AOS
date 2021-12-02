package com.example.tuktalk.data.remote

import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi {

    // top5 멘토 리스트 가져오기
    @GET("api/mentors/top5")
    fun getTop5Mentor(
            @Header("Authorization")userToken: String
    ): Single<Response<ArrayList<Top5MentorResponseDto>>>

    // 직무별 멘토 리스트 가져오기 - 디자인 & IT/개발
    @GET("api/mentors")
    fun getMentorByTask(
            @Header("Authorization")userToken: String,
            @Query("specialty") speciality: String
    ): Single<Response<ArrayList<ByTaskMentorResponseDto>>>
}