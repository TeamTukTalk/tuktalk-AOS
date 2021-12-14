package com.nemo.tuktalk.domain.usecase.user.activity

import com.nemo.tuktalk.domain.repository.UserRepository

class WishMentorUseCase (
        private val repository: UserRepository
) {
    fun wishMentor(userToken: String, mentorId : Int)
            = repository.wishMentor(userToken, mentorId)
}