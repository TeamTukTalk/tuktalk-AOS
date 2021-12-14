package com.nemo.tuktalk.data.remote.dto.response.mentee

import com.google.gson.annotations.SerializedName

data class MenteeRecentHistoryResponseDto(
        @SerializedName("mentorId")
        val mentorId : Int,
        @SerializedName("mentorNickname")
        val mentorNickname : String,
        @SerializedName("description")
        val description : String,
        @SerializedName("createdDateTime")
        val createdDateTime : String,
        @SerializedName("pdfUrl")
        val pdfUrl : String
)