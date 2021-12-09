package com.nemo.tuktalk.domain.usecase.mentor

import com.nemo.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.nemo.tuktalk.domain.repository.MentorRepository

class MentorRegistProfileUseCase(
        private val repository: MentorRepository
) {
    fun registMentorProfile(userToken: String, mentorProfile: MentorProfileRequestDto) =
            repository.registMentorProfile(userToken, mentorProfile)
}