package com.zoksh.buyzone

import android.app.Application
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
            modules()
        }


    }
}