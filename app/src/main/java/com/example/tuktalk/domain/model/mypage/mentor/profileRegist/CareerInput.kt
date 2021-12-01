package com.example.tuktalk.domain.model.mypage.mentor.profileRegist

import com.google.gson.annotations.SerializedName

data class CareerInput(
        @SerializedName("months")
        var months : Int,
        @SerializedName("years")
        var years : Int
)