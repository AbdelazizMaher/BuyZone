package com.zoksh.feature_categories.di

import com.zoksh.feature_categories.presentation.viewmodel.CategoriesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val categoriesModule = module {
    viewModelOf(::CategoriesViewModel)
}
