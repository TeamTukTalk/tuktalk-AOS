package com.example.tuktalk.domain.usecase.mentor.info

import com.example.tuktalk.domain.repository.MentorRepository

class GetMentorDetailInfoUseCase(
    private val repository: MentorRepository
) {

    fun getMentorDetailInfo(userToken: String, mentorId: Int)
            = repository.getMentorDetailInfo(userToken, mentorId)
}