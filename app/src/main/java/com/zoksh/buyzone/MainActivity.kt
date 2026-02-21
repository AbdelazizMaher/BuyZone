package com.zoksh.buyzone

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.zoksh.buyzone.presentation.BuyZoneApp

class MainActivity : ComponentActivity() {
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BuyZone)
        FacebookSdk.sdkInitialize(applicationContext)
        enableEdgeToEdge()

        setContent {
            BuyZoneApp(callbackManager = callbackManager)
        }
    }

    @Deprecated("This method has been deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
