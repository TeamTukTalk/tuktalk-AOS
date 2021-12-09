package com.nemo.tuktalk.domain.usecase.user

import com.nemo.tuktalk.domain.repository.UserRepository

class UserEmailCheckUseCase (
        private val repository: UserRepository
) {
    fun getUserEmailCheckResult(email: String)
            = repository.userEmailCheck(email)
}