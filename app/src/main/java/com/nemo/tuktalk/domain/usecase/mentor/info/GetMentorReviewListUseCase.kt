package com.nemo.tuktalk.domain.usecase.mentor.info

import com.nemo.tuktalk.domain.repository.MentorRepository

class GetMentorReviewListUseCase (
        private val repository: MentorRepository
) {

    fun getMentorReviewList(userToken: String, mentorId: Int)
            = repository.getMentorReviewList(userToken, mentorId)
}