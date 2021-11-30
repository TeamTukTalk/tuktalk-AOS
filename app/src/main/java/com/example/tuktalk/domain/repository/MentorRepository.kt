package com.example.tuktalk.domain.repository

import com.example.tuktalk.data.remote.dto.response.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.UserEmailCheckDto
import io.reactivex.Single
import retrofit2.Response

interface MentorRepository {

    // 멘토 기업 메일 인증 여부 체크
    fun mentorEmailCertificationCheck(userToken: String) : Single<Response<MentorEmailCertificationResponseDto>>
    fun sendEmailCertification(userToken: String, email: String) : Single<Response<Void>>
}