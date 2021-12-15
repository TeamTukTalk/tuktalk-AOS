package com.nemo.tuktalk.data.remote.dto.response.mentee.review

import com.google.gson.annotations.SerializedName
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseDto

data class MenteeReviewListResponseDtoList(
        @SerializedName("reviews")
        val reviews : ArrayList<MenteeReviewListResponseDto>
)