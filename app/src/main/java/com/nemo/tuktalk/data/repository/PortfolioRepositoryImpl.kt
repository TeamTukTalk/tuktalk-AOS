package com.nemo.tuktalk.data.repository

import com.nemo.tuktalk.data.remote.PortfolioApi
import com.nemo.tuktalk.data.remote.dto.request.mentor.RegistPortfolioRequestDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.FileUploadResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.data.remote.dto.response.portfolio.RegistPortfolioResponseDto
import com.nemo.tuktalk.domain.repository.PortfolioRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Response

class PortfolioRepositoryImpl(
        private val portfolioApi: PortfolioApi
): PortfolioRepository {

    // 포트폴리오 상세 정보 조회
    override fun getPortfolioDetailInfo(
        userToken: String,
        mentorId: Int
    ): Single<Response<PortfolioDetailInfoResponseDto>> =
        portfolioApi.getMentorDetailInfo(userToken, mentorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    // 포트폴리오 pdf 파일 업로드
    override fun uploadPdfFile(userToken: String, pdfFile: MultipartBody.Part): Single<Response<FileUploadResponseDto>>
        = portfolioApi.uploadPdfFiles(userToken, pdfFile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    // 포트폴리오 최종 정보 등록
    override fun registPortfolio(userToken: String, portfolioData: RegistPortfolioRequestDto): Single<Response<RegistPortfolioResponseDto>>
         = portfolioApi.registPortfolio(userToken, portfolioData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}