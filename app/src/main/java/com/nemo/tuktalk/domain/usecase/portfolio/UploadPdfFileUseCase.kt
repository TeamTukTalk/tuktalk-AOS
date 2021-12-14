package com.nemo.tuktalk.domain.usecase.portfolio

import com.nemo.tuktalk.domain.repository.PortfolioRepository

class UploadPdfFileUseCase (
        private val repository: PortfolioRepository
) {

    fun uploadPdfFile(userToken: String, encodedPdf: String)
            = repository.uploadPdfFile(userToken, encodedPdf)
}