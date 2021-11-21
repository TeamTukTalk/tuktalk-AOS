package com.example.tuktalk.di

import com.example.tuktalk.presentation.login.LoginViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
import com.example.tuktalk.presentation.signup.info.InfoRegistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { InfoRegistViewModel(get(), get()) }
    viewModel { LoginViewModel(get())}
    viewModel { MentorRegistViewModel() }
}