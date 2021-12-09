package com.nemo.tuktalk.di

import com.nemo.tuktalk.domain.usecase.home.HomeByTaskMentorListUseCase
import com.nemo.tuktalk.domain.usecase.home.HomeTop5MentorListUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorGetCompanyNameUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorRegistProfileUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorSendEmailCertificationUseCase
import com.nemo.tuktalk.domain.usecase.mentor.info.GetMentorDetailInfoUseCase
import com.nemo.tuktalk.domain.usecase.portfolio.GetPortfolioDetailInfoUseCase
import com.nemo.tuktalk.domain.usecase.search.SearchMentorListUseCase
import com.nemo.tuktalk.domain.usecase.user.UserEmailCheckUseCase
import com.nemo.tuktalk.domain.usecase.user.UserLoginUseCase
import com.nemo.tuktalk.domain.usecase.user.UserSignUpUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { UserEmailCheckUseCase(get()) }
    factory { UserSignUpUseCase(get())}
    factory { UserLoginUseCase(get()) }

    factory { MentorEmailCertificationCheckUseCase(get()) }
    factory { MentorSendEmailCertificationUseCase(get())}
    factory { MentorGetCompanyNameUseCase(get()) }
    factory { MentorRegistProfileUseCase(get()) }

    factory { HomeTop5MentorListUseCase(get()) }
    factory { HomeByTaskMentorListUseCase(get()) }

    factory { SearchMentorListUseCase(get()) }

    factory { GetMentorDetailInfoUseCase(get()) }
    factory { GetPortfolioDetailInfoUseCase(get()) }


}