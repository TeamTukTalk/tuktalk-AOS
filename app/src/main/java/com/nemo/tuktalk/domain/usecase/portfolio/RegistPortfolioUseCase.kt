package com.nemo.tuktalk.domain.usecase.portfolio

import com.nemo.tuktalk.data.remote.dto.request.mentor.RegistPortfolioRequestDto
import com.nemo.tuktalk.domain.repository.PortfolioRepository
import okhttp3.MultipartBody

class RegistPortfolioUseCase (
        private val repository: PortfolioRepository
) {

    fun registPortfolio(userToken: String, portfolioData: RegistPortfolioRequestDto)
            = repository.registPortfolio(userToken, portfolioData)
}