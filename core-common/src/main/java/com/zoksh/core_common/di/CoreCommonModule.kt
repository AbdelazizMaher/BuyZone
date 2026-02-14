package com.zoksh.core_common.di

import com.zoksh.core_common.data.connectivity.ConnectivityObserverImpl
import com.zoksh.core_common.domain.connectivity.ConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreCommonModule = module {
    single<ConnectivityObserver> { ConnectivityObserverImpl(androidContext()) }
}
