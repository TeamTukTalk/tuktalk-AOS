package com.nemo.tuktalk.domain.model.home

import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseDto

/**
 * Domain layer 에서 필요한 데이터만 Dto 에서 가져와서 model 로 사용
 */

data class RealTimeReviewRVitem(
        var itemviewType : Int,
        var reviewResponseDto: ReviewResponseDto
)