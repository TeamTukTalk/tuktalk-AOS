package com.nemo.tuktalk.data.remote.dto.response.home.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseDtoList(
        @SerializedName("reviews")
        val reviews : ArrayList<ReviewResponseDto>
)