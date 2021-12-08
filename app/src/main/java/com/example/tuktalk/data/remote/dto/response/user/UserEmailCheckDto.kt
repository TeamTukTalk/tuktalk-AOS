package com.example.tuktalk.data.remote.dto.response.user

import com.google.gson.annotations.SerializedName

data class UserEmailCheckDto(
        @SerializedName("existingEmail")
        val isEmailExist : Boolean
)