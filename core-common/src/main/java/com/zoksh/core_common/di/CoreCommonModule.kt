package com.zoksh.core_common.di

import com.zoksh.core_common.data.connectivity.ConnectivityObserverImpl
import com.zoksh.core_common.domain.connectivity.ConnectivityObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreCommonModule = module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    single<ConnectivityObserver> { ConnectivityObserverImpl(androidContext()) }
}
