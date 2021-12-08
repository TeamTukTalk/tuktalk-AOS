package com.example.tuktalk.domain.usecase.user

import com.example.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.example.tuktalk.domain.repository.UserRepository

class UserSignUpUseCase(
        private val repository: UserRepository
) {
    fun userSignUp(userSignUpRequestDto: UserSignUpRequestDto)
            = repository.userSignUp(userSignUpRequestDto)
}