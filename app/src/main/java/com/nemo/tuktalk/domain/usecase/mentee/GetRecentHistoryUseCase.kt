package com.nemo.tuktalk.domain.usecase.mentee

import com.nemo.tuktalk.domain.repository.MenteeRepository

class GetRecentHistoryUseCase (
        private val repository: MenteeRepository
) {

    fun getRecentHistory(userToken : String) = repository.getRecentHistory(userToken)
}