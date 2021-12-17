package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDtoList
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.info.MentorDetailInfoResponseDto
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


    // 멘토 상세정보 조회
    @GET("api/mentors/{mentorId}")
    fun getMentorDetailInfo(
        @Header("Authorization")userToken: String,
        @Path("mentorId")mentorId : Int
    ) : Single<Response<MentorDetailInfoResponseDto>>



    // 해당 멘토의 후기 리스트 가져오기
    @GET("api/reviews-mentor")
    fun getMentorReviewList(
            @Header("Authorization")userToken: String,
            @Query("mentorId") mentorId: Int
    ) : Single<Response<MenteeReviewListResponseDtoList>>


}