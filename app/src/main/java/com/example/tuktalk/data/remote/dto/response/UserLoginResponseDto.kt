package com.example.tuktalk.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
        @SerializedName("accessToken")
        val accessToken : String,
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("email")
        val email : String
)