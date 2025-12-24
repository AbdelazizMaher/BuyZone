package com.zoksh.feature_onboarding.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class OnboardingPreferencesImpl(
    private val sharedPreferences: SharedPreferences
) : OnBoardingPreferences {
    override fun shouldShowOnBoarding(): Boolean {
        return sharedPreferences.getBoolean(PreferenceConfig.ON_BOARDING_SEEN_KEY, true)
    }

    override fun markOnBoardingAsSeen() {
        sharedPreferences.edit {
            putBoolean(PreferenceConfig.ON_BOARDING_SEEN_KEY, false)
        }
    }
}