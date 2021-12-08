package com.example.tuktalk.data.repository

import com.example.tuktalk.data.remote.UserApi
import com.example.tuktalk.data.remote.dto.request.user.UserLoginRequestDto
import com.example.tuktalk.data.remote.dto.response.user.UserEmailCheckDto
import com.example.tuktalk.data.remote.dto.request.user.UserSignUpRequestDto
import com.example.tuktalk.data.remote.dto.response.user.UserLoginResponseDto
import com.example.tuktalk.data.remote.dto.response.user.UserSignUpResponseDto
import com.example.tuktalk.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(
        private val userApi: UserApi
) : UserRepository {

    // 이메일 중복 체크
    override fun userEmailCheck(email: String): Single<UserEmailCheckDto> =
            userApi.getEmailCheckResult(email)
                    .delay(180L, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    // 가입
    override fun userSignUp(userSignUpRequestDto: UserSignUpRequestDto): Single<Response<UserSignUpResponseDto>> =
            userApi.userSignUp(userSignUpRequestDto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    // 로그인
    override fun userLogin(userLoginRequestDto: UserLoginRequestDto): Single<Response<UserLoginResponseDto>> =
            userApi.userLogin(userLoginRequestDto)
                    .delay(100L, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}