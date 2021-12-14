package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
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
}