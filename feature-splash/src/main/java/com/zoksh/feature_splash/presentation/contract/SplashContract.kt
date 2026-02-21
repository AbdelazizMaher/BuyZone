package com.zoksh.feature_splash.presentation.contract

import androidx.compose.runtime.Immutable

sealed interface SplashContract {

    @Immutable
    data object State

    sealed interface Intent {
        data object Initialize : Intent
    }

    sealed interface Effect {
        data object OnFinished : Effect
    }
}
