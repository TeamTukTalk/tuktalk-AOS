package com.example.tuktalk.di

import com.example.tuktalk.data.repository.HomeRepositoryImpl
import com.example.tuktalk.data.repository.MentorRepositoryImpl
import com.example.tuktalk.data.repository.SearchRepositoryImpl
import com.example.tuktalk.data.repository.UserRepositoryImpl
import com.example.tuktalk.domain.repository.HomeRepository
import com.example.tuktalk.domain.repository.MentorRepository
import com.example.tuktalk.domain.repository.SearchRepository
import com.example.tuktalk.domain.repository.UserRepository
import org.koin.dsl.module


internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MentorRepository>{ MentorRepositoryImpl(get())}
    single<HomeRepository>{ HomeRepositoryImpl(get())}
    single<SearchRepository>{ SearchRepositoryImpl(get()) }

}