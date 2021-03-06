package com.nemo.tuktalk.data.remote.dto.request.user

import com.google.gson.annotations.SerializedName

data class UserSignUpRequestDto(
        @SerializedName("subSpecialties")
        val subSpecialities : ArrayList<String>,
        @SerializedName("email")
        val email : String,
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("password")
        val password : String,
        @SerializedName("role")
        val role : String,
        @SerializedName("profileImageColor")
        val profileImageColor : String,
        @SerializedName("firstLetter")
        val firstLetter : String
       /* ,
        @SerializedName("randomProfileImageUrl")
        val randomProfileImageUrl : String*/

)