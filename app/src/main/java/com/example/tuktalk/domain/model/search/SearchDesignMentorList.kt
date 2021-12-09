package com.example.tuktalk.domain.model.search

import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto

data class SearchDesignMentorList(
    var itemViewType : Int,
    var searchMentorResponseDto: SearchMentorResponseDto
)