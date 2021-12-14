package com.nemo.tuktalk.data.remote.dto.response.home.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseDto(
        @SerializedName("mentor")
        val mentor : ReviewResponseMentor,
        @SerializedName("rating")
        val rating : Int,
        @SerializedName("description")
        val reviewDescription : String,
        @SerializedName("mentee")
        val mentee : ReviewResponseMentee

)