package com.example.tuktalk.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserEmailCheckDto(
        @SerializedName("existingEmail")
        val isEmailExist : Boolean
)