package com.nemo.tuktalk.data.remote.dto.response.home.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseDto(
        @SerializedName("reviewId")
        val reviewId : Int,
        @SerializedName("mentor")
        val mentor : ReviewResponseMentor,
        @SerializedName("rating")
        val rating : Int,
        @SerializedName("description")
        val reviewDescription : String,
        @SerializedName("mentee")
        val mentee : ReviewResponseMentee,
        @SerializedName("portfolioViewedDateTime")
        val portfolioViewedDateTime : String,
        @SerializedName("reviewCreatedDateTime")
        val reviewCreatedDateTime : String,
        @SerializedName("profileImageColor")
        val profileImageColor : String,
        @SerializedName("firstLetter")
        val firstLetter : String

)