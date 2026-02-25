package com.zoksh.buyzone

import android.app.Application
import com.zoksh.buyzone.di.appModule
import com.zoksh.core_common.di.coreCommonModule
import com.zoksh.core_session.di.sessionModule
import com.zoksh.feature_authentication.di.authModule
import com.zoksh.feature_cart.di.cartModule
import com.zoksh.feature_categories.di.categoriesModule
import com.zoksh.feature_details.di.detailsModule
import com.zoksh.feature_home.di.homeModule
import com.zoksh.feature_onboarding.di.onBoardingModule
import com.zoksh.feature_search.di.searchModule
import com.zoksh.feature_splash.di.splashModule
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
                appModule,
                coreCommonModule,
                onBoardingModule,
                authModule,
                homeModule,
                searchModule,
                detailsModule,
                categoriesModule,
                cartModule,
                sessionModule,
                apolloModule,
                splashModule
            )
        }
    }
}
