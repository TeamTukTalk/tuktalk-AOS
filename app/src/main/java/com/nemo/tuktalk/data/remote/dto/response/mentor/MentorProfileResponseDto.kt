package com.nemo.tuktalk.data.remote.dto.response.mentor

import com.google.gson.annotations.SerializedName

data class MentorProfileResponseDto(
        @SerializedName("mentorId")
        val mentorId : Int
)