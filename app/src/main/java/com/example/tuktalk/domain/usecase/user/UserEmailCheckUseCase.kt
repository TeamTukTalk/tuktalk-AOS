package com.example.tuktalk.domain.usecase.user

import com.example.tuktalk.domain.repository.UserRepository

class UserEmailCheckUseCase (
        private val repository: UserRepository
) {
    fun getUserEmailCheckResult(email: String)
            = repository.userEmailCheck(email)
}