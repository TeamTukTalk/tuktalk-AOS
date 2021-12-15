package com.nemo.tuktalk.domain.usecase.mentee

import com.nemo.tuktalk.data.remote.dto.request.mentee.WriteReviewRequestDto
import com.nemo.tuktalk.domain.repository.MenteeRepository

class GetReviewListUseCase (
        private val repository: MenteeRepository
) {

    fun getReviewList(userToken : String) = repository.getMenteeReviewList(userToken)
}