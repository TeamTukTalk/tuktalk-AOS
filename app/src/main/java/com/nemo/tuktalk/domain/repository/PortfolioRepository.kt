package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.request.mentor.RegistPortfolioRequestDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.FileUploadResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.RegistPortfolioResponseDto
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response

interface PortfolioRepository {

    // 포트폴리오 상제정보 조회
    fun getPortfolioDetailInfo(userToken: String, mentorId: Int): Single<Response<PortfolioDetailInfoResponseDto>>

    // 포트폴리오 pdf 파일 업로드
    fun uploadPdfFile(userToken: String, pdfFile: MultipartBody.Part) : Single<Response<FileUploadResponseDto>>

    // 포트폴리오 이미지 파일 업로드

    // 포트폴리오 정보 최종 등록
    fun registPortfolio(userToken: String, portfolioData: RegistPortfolioRequestDto) : Single<Response<RegistPortfolioResponseDto>>
}