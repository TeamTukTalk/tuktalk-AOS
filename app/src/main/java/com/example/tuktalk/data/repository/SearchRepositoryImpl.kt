package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.MentorApi
import com.example.tuktalk.data.remote.SearchApi
import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.example.tuktalk.domain.repository.SearchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SearchRepositoryImpl(
        private val searchApi: SearchApi
) : SearchRepository {

    // 멘토 리스트 조회
    override fun searchMentorList(userToken: String, tag1: Map<String, String>, tag2 : Map<String, Int>): Single<Response<ArrayList<SearchMentorResponseDto>>> =
            searchApi.searchMentorList(userToken, tag1, tag2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}