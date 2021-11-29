package com.example.tuktalk.di

import com.example.tuktalk.data.repository.MentorRepositoryImpl
import com.example.tuktalk.data.repository.UserRepositoryImpl
import com.example.tuktalk.domain.repository.MentorRepository
import com.example.tuktalk.domain.repository.UserRepository
import org.koin.dsl.module


internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MentorRepository>{ MentorRepositoryImpl(get())}

}