package com.nemo.tuktalk.domain.usecase.mentor

import com.nemo.tuktalk.domain.repository.MentorRepository

class MentorEmailCertificationCheckUseCase(
        private val repository: MentorRepository
) {

    fun getMentorEmailCertificationResult(userToken: String)
        = repository.mentorEmailCertificationCheck(userToken)
}