package com.example.tuktalk.di

import com.example.tuktalk.data.repository.*
import com.example.tuktalk.domain.repository.*
import org.koin.dsl.module


internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MentorRepository>{ MentorRepositoryImpl(get())}
    single<HomeRepository>{ HomeRepositoryImpl(get())}
    single<SearchRepository>{ SearchRepositoryImpl(get()) }
    single<PortfolioRepository>{ PortfolioRepositoryImpl(get()) }

}