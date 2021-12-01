package com.example.tuktalk.data.remote.dto.response.mentor

import com.google.gson.annotations.SerializedName

data class MentorEmailCertificationResponseDto(
        @SerializedName("certifiedMentor")
        val certifiedMentor : Boolean
)
