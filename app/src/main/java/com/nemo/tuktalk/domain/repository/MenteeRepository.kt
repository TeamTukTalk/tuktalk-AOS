package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.request.mentee.WriteReviewRequestDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.WriteReviewResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.review.MenteeReviewListResponseDtoList
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import io.reactivex.Single
import retrofit2.Response

interface MenteeRepository {

    // 멘티 찜목록 가져오기
    fun getMenteeWishList(userToken: String) : Single<Response<ArrayList<MenteeWishListResponseDto>>>

    // 포트폴리오 열람 시 기록 추가
    fun viewPortfolio(userToken: String, portfolioId: Int) : Single<Response<Void>>

    // 최근 열람 기록 리스트 조회
    fun getRecentHistory(userToken: String) : Single<Response<ArrayList<MenteeRecentHistoryResponseDto>>>

    // 후기 등록
    fun writeReview(userToken: String, writeReviewDto : WriteReviewRequestDto): Single<Response<WriteReviewResponseDto>>


    // 멘티 후기 리스트 가져오기
    fun getMenteeReviewList(userToken: String) : Single<Response<MenteeReviewListResponseDtoList>>

    // 멘티 -> 멘토 전환하기
    fun menteeTomentor(userToken: String) : Single<Response<Void>>
}