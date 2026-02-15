package com.zoksh.buyzone.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.facebook.CallbackManager
import com.zoksh.buyzone.navigation.auth.LoginNavHandler
import com.zoksh.buyzone.navigation.auth.SignupNavHandler
import com.zoksh.buyzone.navigation.home.HomeNavHandler
import com.zoksh.buyzone.navigation.onboarding.OnBoardingNavHandler
import com.zoksh.buyzone.session.SessionAction
import com.zoksh.buyzone.session.SessionManager
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_authentication.presentation.login.screen.LoginScreen
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.screen.SignupScreen
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import com.zoksh.feature_home.presentation.navigation.HomeDestination
import com.zoksh.feature_home.presentation.screen.HomeScreen
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel
import com.zoksh.feature_onboarding.presentation.navigation.OnBoardingDestination
import com.zoksh.feature_onboarding.presentation.screen.OnBoardingScreen
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavHost(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    snackBarHostState: SnackbarHostState,
    callbackManager: CallbackManager,
    innerPadding: PaddingValues
) {
    val sessionManager: SessionManager = koinInject()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        sessionManager.startObserving(scope)
    }

    ObserveAsEvents(sessionManager.navigationEvents) { action ->
        when (action) {
            is SessionAction.Logout -> {
                navController.navigate(AuthDestination.Login) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = OnBoardingDestination.OnBoarding,
    ) {
        composable<OnBoardingDestination.OnBoarding> {
            bottomBarState.value = false
            val viewModel: OnBoardingViewModel = koinViewModel()
            OnBoardingNavHandler(navController, viewModel)
            OnBoardingScreen(viewModel = viewModel)
        }
        composable<AuthDestination.Login> {
            bottomBarState.value = false
            val viewModel: LoginViewModel = koinViewModel()
            LoginNavHandler(navController, viewModel, snackBarHostState, callbackManager)
            LoginScreen(
                viewModel = viewModel, innerPadding = innerPadding
            )
        }
        composable<AuthDestination.SignUp> {
            bottomBarState.value = false
            val viewModel: SignupViewModel = koinViewModel()
            SignupNavHandler(navController, viewModel, snackBarHostState)
            SignupScreen(
                viewModel = viewModel, innerPadding = innerPadding
            )
        }
        composable<HomeDestination.Home> {
            bottomBarState.value = true
            val viewModel: HomeViewModel = koinViewModel()
            HomeNavHandler(navController, viewModel)
            HomeScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onIntent = viewModel::handleIntent,
                innerPadding = innerPadding
            )
        }
    }
}
