package com.nemo.tuktalk.domain.usecase.user

import com.nemo.tuktalk.domain.repository.UserRepository

class GetUserInfoUseCase (
        private val repository: UserRepository
) {
    fun getUserInfo(userToken: String) = repository.getUserInfo(userToken)
}