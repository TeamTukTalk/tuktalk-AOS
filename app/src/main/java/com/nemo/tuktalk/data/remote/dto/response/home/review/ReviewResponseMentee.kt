package com.nemo.tuktalk.data.remote.dto.response.home.review

import com.google.gson.annotations.SerializedName

data class ReviewResponseMentee(
        @SerializedName("nickname")
        val menteeNickname : String
)