package com.example.tuktalk.di

import com.example.tuktalk.domain.usecase.user.UserEmailCheckUseCase
import com.example.tuktalk.domain.usecase.user.UserLoginUseCase
import com.example.tuktalk.domain.usecase.user.UserSignUpUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { UserEmailCheckUseCase(get()) }
    factory { UserSignUpUseCase(get())}
    factory { UserLoginUseCase(get()) }
}