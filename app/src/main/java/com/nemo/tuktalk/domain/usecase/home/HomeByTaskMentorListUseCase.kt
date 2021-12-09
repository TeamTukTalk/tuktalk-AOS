package com.nemo.tuktalk.domain.usecase.home

import com.nemo.tuktalk.domain.repository.HomeRepository

class HomeByTaskMentorListUseCase(
        private val repository: HomeRepository
) {

    fun getByTaskMentorList(userToken: String, speciality: String) = repository.getByTaskMentorList(userToken, speciality)
}