package com.nemo.tuktalk.domain.model.mypage.mentee.reviewlist

import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDto

data class ReviewListRvItem(
        var itemViewType : Int,
        var menteeReviewListResponseDto : MenteeReviewListResponseDto
)