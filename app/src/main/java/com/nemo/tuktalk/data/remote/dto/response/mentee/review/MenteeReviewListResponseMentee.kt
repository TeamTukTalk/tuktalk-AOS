package com.nemo.tuktalk.data.remote.dto.response.mentee.review

import com.google.gson.annotations.SerializedName

data class MenteeReviewListResponseMentee(
        @SerializedName("nickname")
        val menteeNickname : String
)