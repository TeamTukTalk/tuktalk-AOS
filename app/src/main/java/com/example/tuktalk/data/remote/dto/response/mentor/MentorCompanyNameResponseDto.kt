package com.example.tuktalk.data.remote.dto.response.mentor

import com.google.gson.annotations.SerializedName

data class MentorCompanyNameResponseDto(
    @SerializedName("companyName")
    val companyName : String
)