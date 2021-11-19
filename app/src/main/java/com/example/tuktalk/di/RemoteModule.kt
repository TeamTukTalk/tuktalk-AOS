package com.example.tuktalk.di

import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.data.remote.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal val remoteModule = module {
    single { provideRetrofitBuild_Rx() }
    single { provideUserApi(get()) }

}

internal fun provideUserApi(retrofit: Retrofit) : UserApi = retrofit.create(UserApi::class.java)


internal fun provideRetrofitBuild_Rx() = Retrofit.Builder()
        .baseUrl(Constants_gitignore.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkhttpClient())
        .build()

internal fun getOkhttpClient() = OkHttpClient.Builder().apply {

    //TimeOut 시간을 지정합니다.
    readTimeout(30, TimeUnit.SECONDS)
    connectTimeout(30, TimeUnit.SECONDS)
    writeTimeout(30, TimeUnit.SECONDS)

    // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
    addInterceptor(getLoggingInterceptor())
}.build()

internal fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

