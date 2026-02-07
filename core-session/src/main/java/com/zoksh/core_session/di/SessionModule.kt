package com.zoksh.core_session.di

import com.zoksh.core_session.identity.store.UserStore
import com.zoksh.core_session.identity.store.UserStoreImpl
import com.zoksh.core_session.identity.store.secure_storage.UserStorage
import com.zoksh.core_session.identity.store.secure_storage.UserStorageImpl
import com.zoksh.core_session.session.store.SecureSessionStore
import com.zoksh.core_session.session.store.secure_storage.SecureStorage
import com.zoksh.core_session.session.store.secure_storage.SecureStorageImpl
import com.zoksh.core_session.session.store.SessionStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sessionModule = module {
    single<SecureStorage> { SecureStorageImpl(androidContext()) }
    single<SessionStore> { SecureSessionStore(get()) }

    single<UserStorage> { UserStorageImpl(androidContext()) }
    single<UserStore> { UserStoreImpl(get()) }
}