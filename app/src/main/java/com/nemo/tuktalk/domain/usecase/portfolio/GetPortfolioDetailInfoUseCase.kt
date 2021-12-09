package com.nemo.tuktalk.domain.usecase.portfolio

import com.nemo.tuktalk.domain.repository.PortfolioRepository

class GetPortfolioDetailInfoUseCase(
    private val repository: PortfolioRepository
) {

    fun getPortfolioDetailInfo(userToken: String, mentorId: Int)
            = repository.getPortfolioDetailInfo(userToken, mentorId)
}