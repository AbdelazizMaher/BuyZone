package com.zoksh.buyzone

import android.app.Application
import com.zoksh.feature_authentication.di.authModule
import com.zoksh.feature_onboarding.di.onBoardingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        registerKoin()
    }

    private fun registerKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                onBoardingModule,
                authModule
            )
        }


    }
}