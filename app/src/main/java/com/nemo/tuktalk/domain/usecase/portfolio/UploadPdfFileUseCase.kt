package com.nemo.tuktalk.domain.usecase.portfolio

import com.nemo.tuktalk.domain.repository.PortfolioRepository
import okhttp3.MultipartBody

class UploadPdfFileUseCase (
        private val repository: PortfolioRepository
) {

    fun uploadPdfFile(userToken: String, pdfFile: MultipartBody.Part)
            = repository.uploadPdfFile(userToken, pdfFile)
}