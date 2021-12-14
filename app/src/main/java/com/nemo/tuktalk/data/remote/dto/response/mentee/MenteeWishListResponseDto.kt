package com.nemo.tuktalk.data.remote.dto.response.mentee

import com.google.gson.annotations.SerializedName
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag

data class MenteeWishListResponseDto(
        @SerializedName("wishId")
        val wishId : Int,
        @SerializedName("mentorId")
        val mentorId : Int,
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