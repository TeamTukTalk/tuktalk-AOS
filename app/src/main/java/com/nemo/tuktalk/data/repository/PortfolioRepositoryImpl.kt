package com.nemo.tuktalk.data.repository

import com.nemo.tuktalk.data.remote.PortfolioApi
import com.nemo.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import com.nemo.tuktalk.domain.repository.PortfolioRepository
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