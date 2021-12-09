package com.nemo.tuktalk.domain.usecase.mentor.info

import com.nemo.tuktalk.domain.repository.MentorRepository

class GetMentorDetailInfoUseCase(
    private val repository: MentorRepository
) {

    fun getMentorDetailInfo(userToken: String, mentorId: Int)
            = repository.getMentorDetailInfo(userToken, mentorId)
}