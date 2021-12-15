package com.nemo.tuktalk.domain.repository

import com.nemo.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserEmailCheckDto
import com.nemo.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserLoginResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.UserSignUpResponseDto
import com.nemo.tuktalk.data.remote.dto.response.user.activity.WishMentorResponseDto

import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun userEmailCheck(email: String) : Single<UserEmailCheckDto>
    fun userSignUp(userSignUpRequestDto: UserSignUpRequestDto): Single<Response<UserSignUpResponseDto>>
    fun userLogin(userLoginRequestDto: UserLoginRequestDto): Single<Response<UserLoginResponseDto>>

    // 멘토 찜하기 = 위시리스트 추가
    fun wishMentor(userToken: String, mentorId: Int) : Single<Response<WishMentorResponseDto>>

    // 멘토 찜 취소 하기
    fun deleteWishMentor(userToken: String, wishId: Int) : Single<Response<Void>>
}



/**
 * domain 에 repository interface 를 두는 것의 장점
 * 1. Test 에 용이 (fake 구현체만 바꿔서 주입하면 되므로)
 * 2. Domain 만 봐도 이 앱이 어떤 비즈니스 로직을 수행하는지 한눈에 알아볼 수 있음(?)
 * 레퍼런스 더 찾아보기!!
 */