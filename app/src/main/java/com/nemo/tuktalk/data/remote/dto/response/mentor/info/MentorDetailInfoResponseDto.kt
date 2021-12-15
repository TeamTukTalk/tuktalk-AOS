package com.nemo.tuktalk.data.remote.dto.response.mentor.info

import com.nemo.tuktalk.domain.model.mypage.mentor.info.SubSpecialty
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.CareerInput
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.google.gson.annotations.SerializedName

data class MentorDetailInfoResponseDto(
    @SerializedName("mentorId")
    val mentorId : Int,
    @SerializedName("profileImageUrl")
    val profileImageUrl : String,
    @SerializedName("profileImageColor")
    val profileImageColor : String,
    @SerializedName("firstLetter")
    val firstLetter : String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("simpleIntroduction")
    val simpleIntroduction : String,
    @SerializedName("detailedIntroduction")
    val detailedIntroduction : String,
    @SerializedName("companyName")
    val companyName : String,
    @SerializedName("specialty")
    val specialty : String,
    @SerializedName("subSpecialties")
    val subSpecialties : ArrayList<SubSpecialty>,
    @SerializedName("position")
    val position : String,
    @SerializedName("career")
    val career : CareerInput,
    @SerializedName("careerDescription")
    val careerDescription : String,
    @SerializedName("hashTags")
    val hashTags : ArrayList<HashTag>,
    @SerializedName("addedToWishList")
    val addedToWishList : Boolean
    )