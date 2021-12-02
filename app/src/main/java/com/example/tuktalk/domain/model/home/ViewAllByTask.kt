package com.example.tuktalk.domain.model.home

import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto

data class ViewAllByTask(
        var byTaskMentorResponseDto: ByTaskMentorResponseDto,
        var itemViewType : Int
)