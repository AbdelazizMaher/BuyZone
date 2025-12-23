package com.zoksh.feature_onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.zoksh.feature_onboarding.data.local.OnBoardingPreferences
import com.zoksh.feature_onboarding.data.local.OnboardingPreferencesImpl
import com.zoksh.feature_onboarding.data.local.PreferenceConfig
import com.zoksh.feature_onboarding.data.repository.OnBoardingRepositoryImpl
import com.zoksh.feature_onboarding.domain.repository.OnBoardingRepository
import com.zoksh.feature_onboarding.domain.usecase.MarkOnBoardingAsSeenUseCase
import com.zoksh.feature_onboarding.domain.usecase.ShouldShowOnBoardingUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val OnBoardingModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            PreferenceConfig.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    single<OnBoardingPreferences> {
        OnboardingPreferencesImpl(get())
    }

    single<OnBoardingRepository> {
        OnBoardingRepositoryImpl(get())
    }

    factory {
        MarkOnBoardingAsSeenUseCase(get())
    }

    factory {
        ShouldShowOnBoardingUseCase(get())
    }
}