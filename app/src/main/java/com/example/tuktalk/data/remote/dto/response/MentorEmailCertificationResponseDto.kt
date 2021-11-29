package com.example.tuktalk.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class MentorEmailCertificationResponseDto(
        @SerializedName("certifiedMentor")
        val certifiedMentor : Boolean
)
