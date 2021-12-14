package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.nemo.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.nemo.tuktalk.data.remote.dto.response.home.review.ReviewResponseDtoList
import io.reactivex.Single
import retrofit2.Response
import java.net.ResponseCache

interface HomeRepository {

    // top5 멘토 리스트 조회
    fun getTop5MentorList(userToken: String) : Single<Response<ArrayList<Top5MentorResponseDto>>>

    // 직무별 멘토 리스트 조회
    fun getByTaskMentorList(userToken: String, speciality: String) : Single<Response<ArrayList<ByTaskMentorResponseDto>>>

    // 실시간 후기 리트스 조회
    fun getRealTimeReviewList(userToken: String, page: Int) : Single<Response<ReviewResponseDtoList>>

}