package com.nemo.tuktalk.data.remote.dto.request.user

import com.google.gson.annotations.SerializedName

data class UserLoginRequestDto(
        @SerializedName("email")
        val email : String,
        @SerializedName("password")
        val password : String

)