package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.activity.WishMentorResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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


}