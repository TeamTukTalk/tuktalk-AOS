package com.nemo.tuktalk.domain.model.home

import com.nemo.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto

data class ViewAllByTask(
        var byTaskMentorResponseDto: ByTaskMentorResponseDto,
        var itemViewType : Int
)