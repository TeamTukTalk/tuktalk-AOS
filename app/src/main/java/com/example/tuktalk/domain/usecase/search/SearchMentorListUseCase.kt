package com.example.tuktalk.domain.usecase.search

import com.example.tuktalk.domain.repository.MentorRepository
import com.example.tuktalk.domain.repository.SearchRepository

class SearchMentorListUseCase(
        private val repository: SearchRepository
) { // 탐색 탭에서 찾는 경우

    fun searchMentorList(userToken: String, tag1: Map<String, String>, tag2: Map<String, Int>)
            = repository.searchMentorList(userToken, tag1, tag2)
}