package com.nemo.tuktalk.data.remote.dto.request.mentee

import com.google.gson.annotations.SerializedName

data class WriteReviewRequestDto(
        @SerializedName("mentorId")
        val mentorId : Int,
        @SerializedName("rating")
        val rating : Int,
        @SerializedName("description")
        val description : String
)