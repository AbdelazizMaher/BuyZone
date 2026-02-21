package com.zoksh.feature_splash.di

import com.zoksh.feature_splash.presentation.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    viewModelOf(::SplashViewModel)
}
