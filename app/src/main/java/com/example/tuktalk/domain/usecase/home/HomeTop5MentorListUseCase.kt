package com.example.tuktalk.domain.usecase.home

import com.example.tuktalk.domain.repository.HomeRepository
import com.example.tuktalk.domain.repository.MentorRepository

class HomeTop5MentorListUseCase(
        private val repository: HomeRepository
) {

    fun getTop5MentorList(userToken: String) = repository.getTop5MentorList(userToken)
}