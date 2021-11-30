package com.example.tuktalk.domain.usecase.mentor

import com.example.tuktalk.domain.repository.MentorRepository

class MentorSendEmailCertificationUseCase(
    private val repository: MentorRepository
) {
    fun sendEmailCertification(userToken : String, email:String) =
        repository.sendEmailCertification(userToken, email)

}