package com.zoksh.feature_authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zoksh.feature_authentication.data.repository.AuthenticationRepositoryImpl
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository
import com.zoksh.feature_authentication.domain.usecase.LoginUseCase
import com.zoksh.feature_authentication.domain.usecase.SignupUseCase
import com.zoksh.feature_authentication.domain.usecase.UserSetupUseCase
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(get(), get(), get())
    }

    factory { UserSetupUseCase(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { SignupUseCase(get(), get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}