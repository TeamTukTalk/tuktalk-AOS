package com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory

import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto

data class RecentHistoryItem(
        var itemViewType : Int,
        var menteeRecentHistoryResponseDto: MenteeRecentHistoryResponseDto
)