package com.nemo.tuktalk.domain.usecase.user

import com.nemo.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.nemo.tuktalk.domain.repository.UserRepository

class UserLoginUseCase(
        private val repository: UserRepository
) {
    fun userLogin(userLoginRequestDto: UserLoginRequestDto)
            = repository.userLogin(userLoginRequestDto)
}