package com.zoksh.buyzone

import android.app.Application
import com.zoksh.core_common.di.coreCommonModule
import com.zoksh.core_session.di.sessionModule
import com.zoksh.feature_authentication.di.authModule
import com.zoksh.feature_home.di.homeModule
import com.zoksh.feature_onboarding.di.onBoardingModule
import com.zoksh.network_apollo.apollo.di.apolloModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        registerKoin()
    }

    private fun registerKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                coreCommonModule,
                onBoardingModule,
                authModule,
                homeModule,
                sessionModule,
                apolloModule,
            )
        }
    }
}