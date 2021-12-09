package com.example.tuktalk.domain.usecase.portfolio

import com.example.tuktalk.domain.repository.MentorRepository
import com.example.tuktalk.domain.repository.PortfolioRepository

class GetPortfolioDetailInfoUseCase(
    private val repository: PortfolioRepository
) {

    fun getPortfolioDetailInfo(userToken: String, mentorId: Int)
            = repository.getPortfolioDetailInfo(userToken, mentorId)
}