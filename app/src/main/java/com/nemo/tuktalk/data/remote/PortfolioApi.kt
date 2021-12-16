package com.nemo.tuktalk.data.remote

import com.nemo.tuktalk.data.remote.dto.request.mentor.RegistPortfolioRequestDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.FileUploadResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.RegistPortfolioResponseDto
import io.reactivex.Single
import okhttp3.MultipartBody
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
    @Multipart
    @POST("api/files")
    fun uploadPdfFiles(
            @Header("Authorization")userToken: String,
            @Part pdfFile : MultipartBody.Part
    ) : Single<Response<FileUploadResponseDto>>



    // 포트폴리오 미리보기 이미지 파일 등록하기



    // 포트폴리오 최종 등록하기
    @POST("api/portfolios")
    fun registPortfolio(
            @Header("Authorization")userToken: String,
            @Body portfolioData : RegistPortfolioRequestDto
    ) : Single<Response<RegistPortfolioResponseDto>>




}