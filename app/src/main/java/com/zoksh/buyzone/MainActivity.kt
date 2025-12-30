package com.zoksh.buyzone

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
import com.zoksh.buyzone.navigation.AppNavHost
import com.zoksh.buyzone.ui.theme.BuyZoneTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
//bbbbbbbbbbbbbbbbbommmmmmm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            BuyZoneTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
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