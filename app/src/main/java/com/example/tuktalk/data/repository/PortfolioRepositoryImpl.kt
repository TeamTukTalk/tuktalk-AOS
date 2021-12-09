package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.MentorApi
import com.example.tuktalk.data.remote.PortfolioApi
import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.info.MentorDetailInfoResponseDto
import com.example.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.example.tuktalk.domain.repository.MentorRepository
import com.example.tuktalk.domain.repository.PortfolioRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    
}