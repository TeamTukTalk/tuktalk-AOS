package com.nemo.tuktalk.data.remote.dto.response.mentee.review

import com.google.gson.annotations.SerializedName

data class MenteeReviewListResponseMentor(
        @SerializedName("nickname")
        val mentorNickname : String,
        @SerializedName("companyName")
        val companyName : String,
        @SerializedName("subSpecialty")
        val subSpecialty : String
)