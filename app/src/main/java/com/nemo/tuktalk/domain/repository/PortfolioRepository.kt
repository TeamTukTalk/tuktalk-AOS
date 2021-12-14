package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.response.portfolio.FileUploadResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import io.reactivex.Single
import retrofit2.Response

interface PortfolioRepository {

    // 포트폴리오 상제정보 조회
    fun getPortfolioDetailInfo(userToken: String, mentorId: Int): Single<Response<PortfolioDetailInfoResponseDto>>

    // 포트폴리오 pdf 파일 업로드
    fun uploadPdfFile(userToken: String, encodedPdf : String) : Single<Response<FileUploadResponseDto>>
}