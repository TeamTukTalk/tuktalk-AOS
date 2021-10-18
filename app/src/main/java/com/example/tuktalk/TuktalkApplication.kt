package com.example.tuktalk

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TuktalkApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // koin 사용
        startKoin {
            androidContext(this@TuktalkApplication )

        }
    }
}