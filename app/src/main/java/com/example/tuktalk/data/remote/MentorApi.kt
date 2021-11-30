package com.example.tuktalk.data.remote

import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.dto.response.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.UserEmailCheckDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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
}