package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.MentorApi
import com.example.tuktalk.data.remote.UserApi
import com.example.tuktalk.data.remote.dto.response.MentorEmailCertificationResponseDto
import com.example.tuktalk.domain.repository.MentorRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MentorRepositoryImpl(
        private val mentorApi: MentorApi
): MentorRepository {

    override fun mentorEmailCertificationCheck(userToken: String): Single<Response<MentorEmailCertificationResponseDto>> =
            mentorApi.getEmailCheckResult(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}