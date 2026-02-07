package com.zoksh.core_session.di

import com.zoksh.core_session.session.store.SecureSessionStore
import com.zoksh.core_session.session.store.secure_storage.SecureStorage
import com.zoksh.core_session.session.store.secure_storage.SecureStorageImpl
import com.zoksh.core_session.session.store.SessionStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sessionModule = module {
    single<SecureStorage> { SecureStorageImpl(androidContext()) }
    single<SessionStore> { SecureSessionStore(get()) }
}