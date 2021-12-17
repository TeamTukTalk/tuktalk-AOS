package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.request.mentee.WriteReviewRequestDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.WriteReviewResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDtoList
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.activity.WishMentorResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface MenteeApi {
    
    // 포트폴리오 열람하기
    @POST("api/view-portfolio")
    fun viewPortfolio(
            @Header("Authorization")userToken: String,
            @Query("portfolioId")portfolioId: Int
    ): Single<Response<Void>>

    // 열람 목록 조회하기
    @GET("api/histories")
    fun getRecentHistory(
            @Header("Authorization")userToken: String
    ) : Single<Response<ArrayList<MenteeRecentHistoryResponseDto>>>


    // 찜목록 가져오기
    @GET("api/wishes")
    fun getMenteeWishList(
            @Header("Authorization")userToken: String
    ) : Single<Response<ArrayList<MenteeWishListResponseDto>>>


    // 멘토에게 후기 남기기
    @POST("api/reviews")
    fun writeReview(
            @Header("Authorization")userToken: String,
            @Body reviewDto : WriteReviewRequestDto
    ) : Single<Response<WriteReviewResponseDto>>


    // 멘티의 후기 리스트 가져오기
    @GET("api/reviews-mentee")
    fun getMenteeReviewList(
            @Header("Authorization")userToken: String
    ) : Single<Response<MenteeReviewListResponseDtoList>>
    
    
    // 멘티 -> 멘토  전환하기
    @POST("api/mentees/change-role")
    fun menteeTomentor(
            @Header("Authorization")userToken: String
    ) : Single<Response<Void>>


}