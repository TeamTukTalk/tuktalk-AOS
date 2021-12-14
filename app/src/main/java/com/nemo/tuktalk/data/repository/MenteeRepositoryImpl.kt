package com.nemo.tuktalk.data.repository

import com.nemo.tuktalk.data.remote.HomeApi
import com.nemo.tuktalk.data.remote.MenteeApi
import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeRecentHistoryResponseDto
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


    // 멘티 포트폴리오 열람 기록 등록
    override fun viewPortfolio(userToken: String, portfolioId: Int): Single<Response<Void>> =
            menteeApi.viewPortfolio(userToken, portfolioId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    // 멘티 최근 본 포트폴리오 리스트 조회
    override fun getRecentHistory(userToken: String): Single<Response<ArrayList<MenteeRecentHistoryResponseDto>>> =
            menteeApi.getRecentHistory(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}