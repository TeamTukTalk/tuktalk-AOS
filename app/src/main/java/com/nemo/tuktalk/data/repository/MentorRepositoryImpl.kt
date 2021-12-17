package com.nemo.tuktalk.data.repository

import com.nemo.tuktalk.data.remote.MentorApi
import com.nemo.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDtoList
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.info.MentorDetailInfoResponseDto
import com.nemo.tuktalk.domain.repository.MentorRepository
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


    // 멘토 프로필 등록
    override fun registMentorProfile(userToken: String, mentorProfile: MentorProfileRequestDto): Single<Response<MentorProfileResponseDto>> =
            mentorApi.registMentorProfile(userToken, mentorProfile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    // 멘토 상제정보 조회
    override fun getMentorDetailInfo(
        userToken: String,
        mentorId: Int
    ): Single<Response<MentorDetailInfoResponseDto>> =
        mentorApi.getMentorDetailInfo(userToken, mentorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    // 멘토 후기 리스트 조회
    override fun getMentorReviewList(userToken: String, mentorId: Int): Single<Response<MenteeReviewListResponseDtoList>> =
            mentorApi.getMentorReviewList(userToken, mentorId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())



}