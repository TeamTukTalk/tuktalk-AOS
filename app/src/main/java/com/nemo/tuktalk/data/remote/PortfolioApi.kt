package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface PortfolioApi {


    // 포트폴리오 상세정보 조회
    @GET("api/portfolios")
    fun getMentorDetailInfo(
        @Header("Authorization")userToken: String,
        @Query("mentorId")mentorId : Int
    ) : Single<Response<PortfolioDetailInfoResponseDto>>


}