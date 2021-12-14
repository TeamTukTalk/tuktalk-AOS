package com.nemo.tuktalk.domain.usecase.mentee

import com.nemo.tuktalk.domain.repository.MenteeRepository
import com.nemo.tuktalk.domain.repository.MentorRepository

class GetWishListUseCase(
        private val repository: MenteeRepository
) {

    fun getMenteeWishList(userToken : String) = repository.getMenteeWishList(userToken)
}