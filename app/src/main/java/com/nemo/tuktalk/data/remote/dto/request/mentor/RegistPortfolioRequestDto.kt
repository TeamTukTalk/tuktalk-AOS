package com.nemo.tuktalk.data.remote.dto.request.mentor

import com.google.gson.annotations.SerializedName

data class RegistPortfolioRequestDto(
        @SerializedName("description")
        val description : String,
        @SerializedName("projectCount")
        val projectCount : Int,
        @SerializedName("totalPages")
        val totalPages : Int,
        @SerializedName("startYear")
        val startYear : Int,
        @SerializedName("endYear")
        val endYear : Int,
        @SerializedName("recommendationTargetDescription")
        val recommendationTargetDescription : String,
        @SerializedName("pdfFileId")
        val pdfFileId : Int,
        @SerializedName("imageFileIds")
        val imageFileIds : ArrayList<Int>
)