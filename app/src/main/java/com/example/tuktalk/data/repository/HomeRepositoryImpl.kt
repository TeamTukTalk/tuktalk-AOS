package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.HomeApi
import com.example.tuktalk.data.remote.MentorApi
import com.example.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorCompanyNameResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorEmailCertificationResponseDto
import com.example.tuktalk.data.remote.dto.response.mentor.MentorProfileResponseDto
import com.example.tuktalk.domain.repository.HomeRepository
import com.example.tuktalk.domain.repository.MentorRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class HomeRepositoryImpl(
        private val homeApi: HomeApi
): HomeRepository {

    // top5 멘토 리스트 조회
    override fun getTop5MentorList(userToken: String): Single<Response<ArrayList<Top5MentorResponseDto>>> =
            homeApi.getTop5Mentor(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 직무별 멘토 리스트 조회
    override fun getByTaskMentorList(userToken: String, speciality: String): Single<Response<ArrayList<ByTaskMentorResponseDto>>> =
            homeApi.getMentorByTask(userToken, speciality)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}