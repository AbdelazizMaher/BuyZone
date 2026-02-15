package com.zoksh.buyzone.di

import com.zoksh.buyzone.session.SessionManager
import org.koin.dsl.module

val appModule = module {
    single { SessionManager(get()) }
}
