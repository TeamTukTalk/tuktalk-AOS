package com.nemo.tuktalk.data.remote.dto.response.search

import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.google.gson.annotations.SerializedName

data class SearchMentorResponseDto(
        @SerializedName("mentorId")
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