package com.example.tuktalk.data.remote.dto.request.mentor

import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.CareerInput
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.google.gson.annotations.SerializedName

data class MentorProfileRequestDto(
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("simpleIntroduction")
    val simpleIntroduction : String,
    @SerializedName("detailedIntroduction")
    val detailedIntroduction : String,
    @SerializedName("specialty")
    val specialty : String,
    @SerializedName("subSpecialties")
    val subSpecialties : ArrayList<String>,
    @SerializedName("companyName")
    val companyName : String,
    @SerializedName("department")
    val department : String,
    @SerializedName("position")
    val position : String,
    @SerializedName("career")
    val career : CareerInput,
    @SerializedName("careerDescription")
    val careerDescription : String,
    @SerializedName("companySize")
    val companySize : String,
    @SerializedName("hashTags")
    val hashTags : ArrayList<HashTag>
)