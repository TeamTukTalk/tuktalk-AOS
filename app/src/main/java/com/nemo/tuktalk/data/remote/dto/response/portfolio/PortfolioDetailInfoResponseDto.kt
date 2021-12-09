package com.nemo.tuktalk.data.remote.dto.response.portfolio

import com.google.gson.annotations.SerializedName

data class PortfolioDetailInfoResponseDto (
    @SerializedName("portfolioId")
    val portfolioId : Int,
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
    @SerializedName("pdfTuktalkFile")
    val pdfTuktalkFile : String,
    @SerializedName("imageFiles")
    val imageFiles : ArrayList<String>
)
