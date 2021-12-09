package com.example.tuktalk.domain.repository

import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.info.MentorDetailInfoResponseDto
import com.example.tuktalk.data.remote.dto.response.portfolio.PortfolioDetailInfoResponseDto
import io.reactivex.Single
import retrofit2.Response

interface PortfolioRepository {

    // 포트폴리오 상제정보 조회
    fun getPortfolioDetailInfo(userToken: String, mentorId: Int): Single<Response<PortfolioDetailInfoResponseDto>>
}