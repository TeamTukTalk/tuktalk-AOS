package com.example.tuktalk.domain.usecase.user

import com.example.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.example.tuktalk.domain.repository.UserRepository

class UserLoginUseCase(
        private val repository: UserRepository
) {
    fun userLogin(userLoginRequestDto: UserLoginRequestDto)
            = repository.userLogin(userLoginRequestDto)
}