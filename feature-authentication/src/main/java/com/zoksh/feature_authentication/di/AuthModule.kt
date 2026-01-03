package com.zoksh.feature_authentication.di

import com.zoksh.feature_authentication.domain.usecase.LoginUseCase
import com.zoksh.feature_authentication.domain.usecase.SignupUseCase
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    factory { LoginUseCase(get(), get()) }
    factory { SignupUseCase(get(), get()) }

    viewModel { LoginViewModel() }
    viewModel { SignupViewModel(get()) }
}