package com.nemo.tuktalk.data.remote.dto.response.user

import com.google.gson.annotations.SerializedName

data class UserInfoResponseDto(
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("role")
        val role : String,
        @SerializedName("mentorId")
        val mentorId : Int,
        @SerializedName("menteeId")
        val menteeId : Int
)