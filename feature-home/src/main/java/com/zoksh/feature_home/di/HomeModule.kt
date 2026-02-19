package com.zoksh.feature_home.di

import com.zoksh.feature_home.data.remote.shop.ApolloShopRemoteDataSource
import com.zoksh.feature_home.data.remote.shop.ShopRemoteDataSource
import com.zoksh.feature_home.data.repository.HomeRepositoryImpl
import com.zoksh.feature_home.domain.repository.HomeRepository
import com.zoksh.feature_home.domain.use_case.GetCategoriesUseCase
import com.zoksh.feature_home.domain.use_case.GetHeaderUseCase
import com.zoksh.feature_home.domain.use_case.GetPopularBrandsUseCase
import com.zoksh.feature_home.domain.use_case.GetPromosUseCase
import com.zoksh.feature_home.domain.use_case.GetTrendingProductsUseCase
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<ShopRemoteDataSource> { ApolloShopRemoteDataSource(get(), Dispatchers.IO) }

    single<HomeRepository> { HomeRepositoryImpl(get(), get(), get()) }

    single { GetCategoriesUseCase(get()) }
    single { GetPopularBrandsUseCase(get()) }
    single { GetTrendingProductsUseCase(get()) }
    single { GetHeaderUseCase(get()) }
    single { GetPromosUseCase(get()) }

    viewModel { HomeViewModel() }
}