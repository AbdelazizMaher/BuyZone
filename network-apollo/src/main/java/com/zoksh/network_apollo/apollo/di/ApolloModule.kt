package com.zoksh.network_apollo.apollo.di

import com.zoksh.network_apollo.apollo.client.createApolloClient
import com.zoksh.network_apollo.apollo.interceptor.SessionExpiryInterceptor
import org.koin.dsl.module

val apolloModule = module {
    single { SessionExpiryInterceptor(get()) }
    single { createApolloClient(get()) }
}