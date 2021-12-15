package com.nemo.tuktalk.di

import com.nemo.tuktalk.domain.usecase.home.GetRealTimeReviewListUseCase
import com.nemo.tuktalk.domain.usecase.home.HomeByTaskMentorListUseCase
import com.nemo.tuktalk.domain.usecase.home.HomeTop5MentorListUseCase
import com.nemo.tuktalk.domain.usecase.mentee.*
import com.nemo.tuktalk.domain.usecase.mentor.MentorEmailCertificationCheckUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorGetCompanyNameUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorRegistProfileUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorSendEmailCertificationUseCase
import com.nemo.tuktalk.domain.usecase.mentor.info.GetMentorDetailInfoUseCase
import com.nemo.tuktalk.domain.usecase.portfolio.GetPortfolioDetailInfoUseCase
import com.nemo.tuktalk.domain.usecase.portfolio.UploadPdfFileUseCase
import com.nemo.tuktalk.domain.usecase.search.SearchMentorListUseCase
import com.nemo.tuktalk.domain.usecase.user.UserEmailCheckUseCase
import com.nemo.tuktalk.domain.usecase.user.UserLoginUseCase
import com.nemo.tuktalk.domain.usecase.user.UserSignUpUseCase
import com.nemo.tuktalk.domain.usecase.user.activity.DeleteWishMentorUseCase
import com.nemo.tuktalk.domain.usecase.user.activity.WishMentorUseCase
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
    factory { GetRealTimeReviewListUseCase(get()) }

    factory { SearchMentorListUseCase(get()) }

    factory { GetMentorDetailInfoUseCase(get()) }
    factory { GetPortfolioDetailInfoUseCase(get()) }

    factory { UploadPdfFileUseCase(get()) }  // pdf 파일 등록 수정하기

    factory { WishMentorUseCase(get()) }
    factory { GetWishListUseCase(get()) }
    factory { DeleteWishMentorUseCase(get()) }

    factory { MenteeViewPortfolioUseCase(get()) }
    factory { GetRecentHistoryUseCase(get()) }

    factory { WriteReviewUseCase(get()) }
    factory { GetReviewListUseCase(get()) }



}