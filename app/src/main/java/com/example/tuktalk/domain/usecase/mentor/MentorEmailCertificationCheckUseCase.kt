package com.example.tuktalk.domain.usecase.mentor

import com.example.tuktalk.domain.repository.MentorRepository

class MentorEmailCertificationCheckUseCase(
        private val repository: MentorRepository
) {

    fun getMentorEmailCertificationResult(userToken: String)
        = repository.mentorEmailCertificationCheck(userToken)
}