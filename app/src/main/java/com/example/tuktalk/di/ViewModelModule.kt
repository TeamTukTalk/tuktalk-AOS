package com.example.tuktalk.di

import com.example.tuktalk.presentation.login.LoginViewModel
import com.example.tuktalk.presentation.main.MainActivityViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import com.example.tuktalk.presentation.signup.info.InfoRegistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { InfoRegistViewModel(get(), get()) }
    viewModel { LoginViewModel(get())}
    viewModel { MentorRegistViewModel(get(), get()) }
    viewModel { MentorProfileViewModel(get()) }
    viewModel { MentorInfoViewModel() }
    viewModel { RegistPortfolioViewModel() }

    viewModel { MainActivityViewModel(get()) }

}