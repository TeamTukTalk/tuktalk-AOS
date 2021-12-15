package com.nemo.tuktalk.domain.usecase.user.activity

import com.nemo.tuktalk.domain.repository.UserRepository

class DeleteWishMentorUseCase (
        private val repository: UserRepository
) {
    fun deleteWishMentor(userToken: String, wishId : Int)
            = repository.deleteWishMentor(userToken, wishId)
}