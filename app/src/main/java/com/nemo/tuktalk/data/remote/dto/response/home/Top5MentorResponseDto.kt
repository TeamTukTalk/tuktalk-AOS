package com.nemo.tuktalk.data.remote.dto.response.home

import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.google.gson.annotations.SerializedName

data class Top5MentorResponseDto(
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