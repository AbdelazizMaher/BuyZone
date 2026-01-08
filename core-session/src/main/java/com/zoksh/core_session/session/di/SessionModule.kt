package com.zoksh.core_session.session.di

import com.zoksh.core_session.session.SecureSessionStore
import com.zoksh.core_session.session.SecureStorage
import com.zoksh.core_session.session.SecureStorageImpl
import com.zoksh.core_session.session.store.SessionStore
import org.koin.dsl.module

val sessionModule = module {
    single<SecureStorage> { SecureStorageImpl() }
    single<SessionStore> { SecureSessionStore(get()) }
}