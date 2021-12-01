package com.example.tuktalk.domain.usecase.mentor

import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.domain.repository.MentorRepository

class MentorRegistProfileUseCase(
        private val repository: MentorRepository
) {
    fun registMentorProfile(userToken: String, mentorProfile: MentorProfileRequestDto) =
            repository.registMentorProfile(userToken, mentorProfile)
}