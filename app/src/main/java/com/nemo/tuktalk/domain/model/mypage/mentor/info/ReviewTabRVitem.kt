package com.nemo.tuktalk.domain.model.mypage.mentor.info

import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDto

data class ReviewTabRVitem(
        var itemViewType : Int,
        var viewAllSelected : Boolean,
        var menteeReviewListResponseDto : MenteeReviewListResponseDto
)