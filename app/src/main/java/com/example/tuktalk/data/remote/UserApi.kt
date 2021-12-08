package com.example.tuktalk.data.remote

import com.example.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.example.tuktalk.data.remote.dto.response.user.UserEmailCheckDto
import com.example.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.example.tuktalk.data.remote.dto.response.user.UserLoginResponseDto
import com.example.tuktalk.data.remote.dto.response.user.UserSignUpResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    // 이메일 중복 체크
    @GET("api/validate")
    fun getEmailCheckResult(
            @Query("email") email: String
    ): Single<UserEmailCheckDto>

    // 가입하기
    @POST("api/users")
    fun userSignUp(
            @Body userdata: UserSignUpRequestDto
    ):Single<Response<UserSignUpResponseDto>>

    // 로그인
    @POST("api/sign-in")
    fun userLogin(
            @Body userdata: UserLoginRequestDto
    ):Single<Response<UserLoginResponseDto>>
}