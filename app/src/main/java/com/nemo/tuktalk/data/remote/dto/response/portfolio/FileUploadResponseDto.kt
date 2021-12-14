package com.nemo.tuktalk.data.remote.dto.response.portfolio

import com.google.gson.annotations.SerializedName

data class FileUploadResponseDto(
        @SerializedName("id")
        val portfolioId : Int,
        @SerializedName("fileUrl")
        val fileUrl : String
)