package com.zoksh.buyzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zoksh.buyzone.navigation.AppNavHost
import com.zoksh.buyzone.navigation.NavigatorImpl
import com.zoksh.buyzone.ui.theme.BuyZoneTheme
import com.zoksh.core_navigation.Navigator

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            BuyZoneTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {

                    },
                    bottomBar = {

                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController,
                        innerPadding
                    )
                }
            }
        }
    }
}