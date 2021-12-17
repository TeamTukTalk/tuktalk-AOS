package com.nemo.tuktalk.domain.usecase.mentee.change

import com.nemo.tuktalk.domain.repository.MenteeRepository

class MenteeToMentorUseCase  (
        private val repository: MenteeRepository
) {

    fun menteeToMentor(userToken : String) = repository.menteeTomentor(userToken)
}