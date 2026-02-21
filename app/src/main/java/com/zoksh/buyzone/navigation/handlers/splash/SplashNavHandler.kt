package com.zoksh.buyzone.navigation.handlers.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.core_session.identity.model.AppAuthState
import com.zoksh.core_session.identity.provider.AuthStateProvider
import com.zoksh.feature_home.presentation.navigation.HomeDestination
import com.zoksh.feature_onboarding.presentation.navigation.OnBoardingDestination
import com.zoksh.feature_splash.presentation.contract.SplashContract
import com.zoksh.feature_splash.presentation.navigation.SplashDestination
import com.zoksh.feature_splash.presentation.viewmodel.SplashViewModel
import org.koin.compose.koinInject


@Composable
fun SplashNavHandler(
    navController: NavHostController,
    viewModel: SplashViewModel,
    authStateProvider: AuthStateProvider= koinInject()
) {
    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            SplashContract.Effect.OnFinished -> {
                val authState = authStateProvider.authState.value
                val nextDestination = when (authState) {
                    is AppAuthState.Authenticated -> HomeDestination.Home
                    AppAuthState.Guest -> OnBoardingDestination.OnBoarding
                }
                
                navController.navigate(nextDestination) {
                    popUpTo(SplashDestination.Splash) { inclusive = true }
                }
            }
        }
    }
}
