package com.nemo.tuktalk.domain.model.search

import com.nemo.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto

data class SearchItMentorList(
    var itemViewType : Int,
    var searchMentorResponseDto: SearchMentorResponseDto
)