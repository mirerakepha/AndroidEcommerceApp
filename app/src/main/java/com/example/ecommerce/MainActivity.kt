package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.ThemeState
import com.example.ecommerce.ui.theme.rememberThemeState
import com.example.ecommerce.ui.theme.screens.home.HomeScreen
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import com.example.ecommerce.ui.theme.screens.settings.SettingScreen
import com.example.ecommerce.ui.theme.screens.splash.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge content
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            // Force dark mode temporarily for testing
            val themeState = rememberThemeState(initialDarkTheme = true)

            // Wrap app in theme + system bar handling
            EcommerceThemeWithSystemBars(themeState = themeState) {
                EcommerceApp(themeState)
            }
        }
    }
}

@Composable
fun EcommerceThemeWithSystemBars(
    themeState: ThemeState,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = themeState.isDarkTheme

    // Update system bars colors dynamically
    LaunchedEffect(systemUiController, isDarkTheme) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme // white icons in dark theme, black in light theme
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    // Apply Material3 theme
    EcommerceTheme(darkTheme = isDarkTheme) {
        content()
    }
}

@Composable
fun EcommerceApp(themeState: ThemeState) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("settings") {
            SettingScreen(navController, themeState)
        }
    }
}
