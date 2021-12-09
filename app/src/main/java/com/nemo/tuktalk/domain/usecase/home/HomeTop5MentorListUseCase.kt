package com.nemo.tuktalk.domain.usecase.home

import com.nemo.tuktalk.domain.repository.HomeRepository

class HomeTop5MentorListUseCase(
        private val repository: HomeRepository
) {

    fun getTop5MentorList(userToken: String) = repository.getTop5MentorList(userToken)
}