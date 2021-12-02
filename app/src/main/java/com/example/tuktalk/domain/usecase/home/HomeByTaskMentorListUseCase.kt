package com.example.tuktalk.domain.usecase.home

import com.example.tuktalk.domain.repository.HomeRepository
import com.example.tuktalk.domain.repository.MentorRepository

class HomeByTaskMentorListUseCase(
        private val repository: HomeRepository
) {

    fun getByTaskMentorList(userToken: String, speciality: String) = repository.getByTaskMentorList(userToken, speciality)
}