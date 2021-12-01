package com.example.tuktalk.data.remote

import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface MentorApi {

    // 기업 메일 인증 여부 확인하기
    @GET("api/certified-mentor")
    fun getEmailCheckResult(
            @Header("Authorization")userToken: String
    ): Single<Response<MentorEmailCertificationResponseDto>>

    // 기업 메일 전송하기
    @POST("api/mails")
    fun sendEmailCertification(
        @Header("Authorization")userToken: String,
        @Query("email") email: String
    ): Single<Response<Void>>


    // 멘토 회사 이름 가져오기
    @GET("api/mentors/default-mentor-information")
    fun getMentorCompanyName(
        @Header("Authorization")userToken: String,
        @Query("email") userEmail: String
    ): Single<Response<MentorCompanyNameResponseDto>>


    // 멘토 프로필 등록하기
    @POST("api/mentors")
    fun registMentorProfile(
            @Header("Authorization")userToken: String,
            @Body mentorProfile : MentorProfileRequestDto
    ):Single<Response<MentorProfileResponseDto>>
}