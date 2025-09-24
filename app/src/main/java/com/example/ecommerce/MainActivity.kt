package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.screens.home.HomeScreen
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import com.example.ecommerce.ui.theme.screens.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceTheme {
                EcommerceApp()
            }
        }
    }
}

@Composable
fun EcommerceApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("home") {
            HomeScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcommerceAppPreview() {
    EcommerceTheme {
        EcommerceApp()
    }
}
