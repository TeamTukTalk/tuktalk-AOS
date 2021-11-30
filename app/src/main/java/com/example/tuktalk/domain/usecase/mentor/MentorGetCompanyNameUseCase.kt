package com.example.tuktalk.domain.usecase.mentor

import com.example.tuktalk.domain.repository.MentorRepository

class MentorGetCompanyNameUseCase(
    private val repository: MentorRepository
) {
    fun getMentorCompanyName(userToken: String, userEmail : String)
            = repository.getMentorCompanyName(userToken, userEmail)
}