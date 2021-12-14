package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.response.portfolio.FileUploadResponseDto
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


    // 포트폴리오 pdf 파일 등록하기  -> 수정하기
    @FormUrlEncoded
    @POST("api/files")
    fun uploadPdfFiles(
            @Header("Authorization")userToken: String,
            @Field("pdf") encodedPdf : String
    ) : Single<Response<FileUploadResponseDto>>



    // 포트폴리오 미리보기 이미지 파일 등록하기


}