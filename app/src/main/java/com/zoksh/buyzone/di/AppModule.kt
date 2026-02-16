package com.zoksh.buyzone.di

import com.zoksh.buyzone.presentation.MainViewModel
import com.zoksh.buyzone.session.SessionManager
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { SessionManager(get()) }
    viewModelOf(::MainViewModel)
}
