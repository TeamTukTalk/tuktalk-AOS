package com.example.tuktalk.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class UserLoginRequestDto(
        @SerializedName("email")
        val email : String,
        @SerializedName("password")
        val password : String

)