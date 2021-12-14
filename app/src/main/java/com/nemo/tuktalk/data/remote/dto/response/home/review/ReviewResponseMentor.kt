package com.nemo.tuktalk.data.remote.dto.response.home.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseMentor(
        @SerializedName("nickname")
        val mentorNickname : String,
        @SerializedName("companyName")
        val companyName : String,
        @SerializedName("subSpecialty")
        val subSpecialty : String
)