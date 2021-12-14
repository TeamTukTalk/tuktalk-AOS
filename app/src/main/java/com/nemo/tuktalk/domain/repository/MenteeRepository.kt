package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import io.reactivex.Single
import retrofit2.Response

interface MenteeRepository {

    // 멘티 찜목록 가져오기
    fun getMenteeWishList(userToken: String) : Single<Response<ArrayList<MenteeWishListResponseDto>>>
}