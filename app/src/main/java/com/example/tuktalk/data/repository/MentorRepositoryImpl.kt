package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.MentorApi
import com.example.tuktalk.data.remote.UserApi
import com.example.tuktalk.data.remote.dto.response.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.MentorEmailCertificationResponseDto
import com.example.tuktalk.domain.repository.MentorRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MentorRepositoryImpl(
        private val mentorApi: MentorApi
): MentorRepository {

    // 인증 여부 검사
    override fun mentorEmailCertificationCheck(userToken: String): Single<Response<MentorEmailCertificationResponseDto>> =
            mentorApi.getEmailCheckResult(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 인증 메일 보내기
    override fun sendEmailCertification(userToken: String, email: String): Single<Response<Void>> =
        mentorApi.sendEmailCertification(userToken, email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    // 멘토 회사 정보 가져오기 -> 프로필 등록 때 해당 edit text 회사 이름 결과값으로 자동 채우기
    override fun getMentorCompanyName(
        userToken: String,
        userEmail: String
    ): Single<Response<MentorCompanyNameResponseDto>> =
        mentorApi.getMentorCompanyName(userToken, userEmail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())




}