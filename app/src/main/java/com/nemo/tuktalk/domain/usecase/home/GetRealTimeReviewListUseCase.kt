package com.nemo.tuktalk.domain.usecase.home

import com.nemo.tuktalk.domain.repository.HomeRepository

class GetRealTimeReviewListUseCase (
        private val repository: HomeRepository
) {

    fun getRealTimeReviewList(userToken: String, page: Int) = repository.getRealTimeReviewList(userToken, page)
}