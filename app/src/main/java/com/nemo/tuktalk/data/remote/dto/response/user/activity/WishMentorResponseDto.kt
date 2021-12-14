package com.nemo.tuktalk.data.remote.dto.response.user.activity

import com.google.gson.annotations.SerializedName

data class WishMentorResponseDto(
        @SerializedName("wishId")
        val wishId : Int
)