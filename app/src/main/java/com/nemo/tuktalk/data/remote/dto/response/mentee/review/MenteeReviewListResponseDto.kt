package com.nemo.tuktalk.data.remote.dto.response.mentee.review

import com.google.gson.annotations.SerializedName
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseMentee
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseMentor

data class MenteeReviewListResponseDto(
        @SerializedName("reviewId")
        val reviewId : Int,
        @SerializedName("mentor")
        val mentor : MenteeReviewListResponseMentor,
        @SerializedName("rating")
        val rating : Int,
        @SerializedName("description")
        val reviewDescription : String,
        @SerializedName("mentee")
        val mentee : MenteeReviewListResponseMentee,
        @SerializedName("portfolioViewedDateTime")
        val portfolioViewedDateTime : String,
        @SerializedName("reviewCreatedDateTime")
        val reviewCreatedDateTime : String,
        @SerializedName("profileImageColor")
        val profileImageColor : String,
        @SerializedName("firstLetter")
        val firstLetter : String
)