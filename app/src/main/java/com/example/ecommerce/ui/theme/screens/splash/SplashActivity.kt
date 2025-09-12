package com.example.ecommerce.ui.theme.screens.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.EcommerceTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                val navController = rememberNavController()
                SplashScreen(navController = navController)
            }
        }
    }
}
