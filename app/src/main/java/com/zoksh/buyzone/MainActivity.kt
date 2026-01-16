package com.zoksh.buyzone

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.zoksh.buyzone.bottombar.AppBottomBar
import com.zoksh.buyzone.navigation.AppNavHost
import com.zoksh.core_ui.theme.BuyZoneTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext);
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            BuyZoneTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {

                    },
                    bottomBar = {
                        AppBottomBar(navController)
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController,
                        callbackManager,
                        innerPadding
                    )
                }
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}