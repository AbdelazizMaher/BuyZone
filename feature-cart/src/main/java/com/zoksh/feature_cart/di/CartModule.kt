package com.zoksh.feature_cart.di

import com.zoksh.feature_cart.presentation.viewmodel.CartViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cartModule = module {
    viewModelOf(::CartViewModel)
}
