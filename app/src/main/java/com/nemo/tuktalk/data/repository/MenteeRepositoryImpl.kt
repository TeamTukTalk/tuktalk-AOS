package com.nemo.tuktalk.data.repository

import com.nemo.tuktalk.data.remote.HomeApi
import com.nemo.tuktalk.data.remote.MenteeApi
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto
import com.nemo.tuktalk.domain.repository.MenteeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MenteeRepositoryImpl(
        private val menteeApi: MenteeApi
): MenteeRepository {

    // 멘티 찜목록 가져오기
    override fun getMenteeWishList(userToken: String): Single<Response<ArrayList<MenteeWishListResponseDto>>> =
            menteeApi.getMenteeWishList(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}