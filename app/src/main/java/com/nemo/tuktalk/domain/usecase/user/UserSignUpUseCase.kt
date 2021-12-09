package com.nemo.tuktalk.domain.usecase.user

import com.nemo.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.nemo.tuktalk.domain.repository.UserRepository

class UserSignUpUseCase(
        private val repository: UserRepository
) {
    fun userSignUp(userSignUpRequestDto: UserSignUpRequestDto)
            = repository.userSignUp(userSignUpRequestDto)
}