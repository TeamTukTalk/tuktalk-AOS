package com.example.tuktalk

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.tuktalk.di.remoteModule
import com.example.tuktalk.di.repositoryModule
import com.example.tuktalk.di.useCaseModule
import com.example.tuktalk.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TuktalkApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)  // 앱에서는 다크모드 사용x

        // koin 사용
        startKoin {
            androidContext(this@TuktalkApplication )

            modules(remoteModule)
            modules(repositoryModule)
            modules(useCaseModule)
            modules(viewModelModule)
        }
    }
}