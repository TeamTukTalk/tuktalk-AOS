package com.nemo.tuktalk.di

import com.nemo.tuktalk.presentation.home.HomeViewModel
import com.nemo.tuktalk.presentation.home.viewAll.ViewAllByTaskViewModel
import com.nemo.tuktalk.presentation.login.LoginViewModel
import com.nemo.tuktalk.presentation.main.MainActivityViewModel
import com.nemo.tuktalk.presentation.mypage.account.withdrawal.WithdrawalViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.MenteeManageReviewViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview.WriteReviewViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.menteeProfile.MenteeProfileViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.MenteeRecentPortfolioViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.wishlist.MenteeWishListViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.PortfolioOpenViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import com.nemo.tuktalk.presentation.search.viewModel.SearchDesignViewModel
import com.nemo.tuktalk.presentation.search.viewModel.SearchDirectViewModel
import com.nemo.tuktalk.presentation.search.viewModel.SearchItViewModel
import com.nemo.tuktalk.presentation.signup.info.InfoRegistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { InfoRegistViewModel(get(), get()) }
    viewModel { LoginViewModel(get())}
    viewModel { MentorRegistViewModel(get(), get()) }

    viewModel { MentorProfileViewModel(get(), get()) }
    viewModel { MenteeProfileViewModel() }

    viewModel { MentorInfoViewModel(get(), get(), get()) }
    viewModel { RegistPortfolioViewModel(get()) }

    viewModel { MenteeRecentPortfolioViewModel(get()) }

    viewModel { MainActivityViewModel(get()) }

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { ViewAllByTaskViewModel(get()) }

    viewModel { SearchDesignViewModel(get()) }
    viewModel { SearchItViewModel(get()) }
    viewModel { SearchDirectViewModel(get()) }

    viewModel { WithdrawalViewModel() }

    viewModel { MenteeWishListViewModel(get(), get()) }

    viewModel { PortfolioOpenViewModel(get()) }

    viewModel { MenteeManageReviewViewModel(get()) }
    viewModel { WriteReviewViewModel() }

}