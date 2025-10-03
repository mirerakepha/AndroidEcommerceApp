package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.ThemeState
import com.example.ecommerce.ui.theme.rememberThemeState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.core.view.WindowCompat
import com.example.ecommerce.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

    EcommerceTheme(darkTheme = isDarkTheme) {
        content()
    }
}

@Composable
fun EcommerceApp(themeState: ThemeState) {
    AppNavHost()
}
