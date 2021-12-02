package com.example.tuktalk.data.remote.dto.response.home

import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.google.gson.annotations.SerializedName

data class ByTaskMentorResponseDto(
        @SerializedName("id")
        val id : Int,
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("companyName")
        val companyName : String,
        @SerializedName("department")
        val department : String,
        @SerializedName("profileImageUrl")
        val profileImageUrl : String,
        @SerializedName("profileImageColor")
        val profileImageColor : String,
        @SerializedName("firstLetter")
        val firstLetter : String,
        @SerializedName("hashTags")
        val hashTags : ArrayList<HashTag>

)