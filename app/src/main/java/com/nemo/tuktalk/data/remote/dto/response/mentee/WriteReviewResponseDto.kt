package com.nemo.tuktalk.data.remote.dto.response.mentee

import com.google.gson.annotations.SerializedName

data class WriteReviewResponseDto(
        @SerializedName("reviewId")
        val reviewId : Int
)