package com.nemo.tuktalk.domain.model.home

import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseDto


data class RealTimeReviewRVitem2(
        var viewAllSelected : Boolean,
        var itemviewType : Int,
        var reviewResponseDto: ReviewResponseDto
)