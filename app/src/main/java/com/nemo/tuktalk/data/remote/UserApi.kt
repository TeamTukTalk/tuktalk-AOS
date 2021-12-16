package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserEmailCheckDto
import com.nemo.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserLoginResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserSignUpResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.activity.WishMentorResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

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


    // 멘토 찜하기
    @POST("api/wishes")
    fun wishMentor(
            @Header("Authorization")userToken: String,
            @Query("mentorId") mentorId:Int
    ): Single<Response<WishMentorResponseDto>>

    // 멘토 찜 취소하기
    @DELETE("api/wishes/{wishId}")
    fun deleteWishMentor(
            @Header("Authorization")userToken: String,
            @Path("wishId") wishId:Int
    ): Single<Response<Void>>



    // 현재 로그인 유저 정보 알아오기
    @GET("api/user-info")
    fun getUserInfo(
            @Header("Authorization")userToken: String
    ) : Single<Response<UserInfoResponseDto>>


}