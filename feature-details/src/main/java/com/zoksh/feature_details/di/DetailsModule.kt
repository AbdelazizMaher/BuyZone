package com.zoksh.feature_details.di

import com.zoksh.feature_details.presentation.viewmodel.DetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val detailsModule = module {
    viewModelOf(::DetailsViewModel)
}