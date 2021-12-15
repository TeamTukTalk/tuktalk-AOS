package com.nemo.tuktalk.domain.usecase.mentee

import com.nemo.tuktalk.data.remote.dto.request.mentee.WriteReviewRequestDto
import com.nemo.tuktalk.domain.repository.MenteeRepository

class WriteReviewUseCase (
        private val repository: MenteeRepository
) {

    fun writeReview(userToken : String, writeReviewDto: WriteReviewRequestDto) =
            repository.writeReview(userToken, writeReviewDto)
}