package com.example.tuktalk.di

import com.example.tuktalk.presentation.home.HomeViewModel
import com.example.tuktalk.presentation.home.viewAll.ViewAllByTaskViewModel
import com.example.tuktalk.presentation.login.LoginViewModel
import com.example.tuktalk.presentation.main.MainActivityViewModel
import com.example.tuktalk.presentation.mypage.mentee.menteeProfile.MenteeProfileViewModel
import com.example.tuktalk.presentation.mypage.mentee.recentPortfolio.MenteeRecentPortfolioViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import com.example.tuktalk.presentation.search.viewModel.SearchDesignViewModel
import com.example.tuktalk.presentation.search.viewModel.SearchDirectViewModel
import com.example.tuktalk.presentation.search.viewModel.SearchItViewModel
import com.example.tuktalk.presentation.signup.info.InfoRegistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { InfoRegistViewModel(get(), get()) }
    viewModel { LoginViewModel(get())}
    viewModel { MentorRegistViewModel(get(), get()) }

    viewModel { MentorProfileViewModel(get(), get()) }
    viewModel { MenteeProfileViewModel() }

    viewModel { MentorInfoViewModel(get(), get()) }
    viewModel { RegistPortfolioViewModel() }

    viewModel { MenteeRecentPortfolioViewModel() }

    viewModel { MainActivityViewModel(get()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { ViewAllByTaskViewModel(get()) }

    viewModel { SearchDesignViewModel(get()) }
    viewModel { SearchItViewModel(get()) }
    viewModel { SearchDirectViewModel(get()) }

}