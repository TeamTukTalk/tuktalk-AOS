package com.nemo.tuktalk.di

import com.nemo.tuktalk.data.repository.*
import com.nemo.tuktalk.domain.repository.*
import org.koin.dsl.module


internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MentorRepository>{ MentorRepositoryImpl(get())}
    single<HomeRepository>{ HomeRepositoryImpl(get())}
    single<SearchRepository>{ SearchRepositoryImpl(get()) }
    single<PortfolioRepository>{ PortfolioRepositoryImpl(get()) }

}