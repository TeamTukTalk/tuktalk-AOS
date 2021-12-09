package com.nemo.tuktalk.data.remote.dto.response.user

import com.google.gson.annotations.SerializedName

data class UserSignUpResponseDto(
        @SerializedName("accessToken")
        val accessToken : String,
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("email")
        val email : String,
        @SerializedName("profileImageColor")
        val profileImageColor : String,
        @SerializedName("firstLetter")
        val firstLetter : String,
        @SerializedName("role")
        val role : String
)