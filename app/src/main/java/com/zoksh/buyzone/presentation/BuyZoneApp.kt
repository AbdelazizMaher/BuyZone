package com.zoksh.buyzone.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.facebook.CallbackManager
import com.zoksh.buyzone.bottombar.AppBottomBar
import com.zoksh.buyzone.navigation.AppNavHost
import com.zoksh.core_ui.snackbar.component.AppSnackBar
import com.zoksh.core_ui.snackbar.component.AppSnackBarVisuals
import com.zoksh.core_ui.theme.BuyZoneTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BuyZoneApp(
    callbackManager: CallbackManager,
    viewModel: MainViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    BuyZoneTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) { snackBarData ->
                    val visuals = snackBarData.visuals as? AppSnackBarVisuals
                    visuals?.let {
                        AppSnackBar(
                            message = it.appMessage,
                            onDismiss = { snackBarData.dismiss() }
                        )
                    }
                }
            },
            bottomBar = {
                if (viewModel.bottomBarState.value) {
                    AppBottomBar(navController = navController)
                }
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                viewModel = viewModel,
                snackBarHostState = snackBarHostState,
                callbackManager = callbackManager,
                innerPadding = innerPadding
            )
        }
    }
}
