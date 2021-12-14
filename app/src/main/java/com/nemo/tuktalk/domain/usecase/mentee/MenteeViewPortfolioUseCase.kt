package com.nemo.tuktalk.domain.usecase.mentee

import com.nemo.tuktalk.domain.repository.MenteeRepository

class MenteeViewPortfolioUseCase(
        private val repository: MenteeRepository
) {

    fun menteeViewPortfolio(userToken : String, portfolioId: Int) = repository.viewPortfolio(userToken, portfolioId)
}