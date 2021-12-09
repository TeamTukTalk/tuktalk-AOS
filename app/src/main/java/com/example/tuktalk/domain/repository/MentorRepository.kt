package com.example.tuktalk.domain.repository

import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.info.MentorDetailInfoResponseDto
import io.reactivex.Single
import retrofit2.Response

interface MentorRepository {

    // 멘토 기업 메일 인증 여부 체크
    fun mentorEmailCertificationCheck(userToken: String) : Single<Response<MentorEmailCertificationResponseDto>>

    // 인증 메일 전송
    fun sendEmailCertification(userToken: String, email: String) : Single<Response<Void>>

    // 인증된 멘토의 회사이름 가져오기
    fun getMentorCompanyName(userToken: String, userEmail: String) : Single<Response<MentorCompanyNameResponseDto>>

    // 멘토 프로필 등록하기
    fun registMentorProfile(userToken: String, mentorProfile : MentorProfileRequestDto): Single<Response<MentorProfileResponseDto>>

    // 멘토 상제정보 조회
    fun getMentorDetailInfo(userToken: String, mentorId: Int): Single<Response<MentorDetailInfoResponseDto>>
}