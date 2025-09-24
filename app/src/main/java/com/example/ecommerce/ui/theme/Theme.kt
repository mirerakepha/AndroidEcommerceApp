package com.example.ecommerce.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


// Light Color
private val LightColorScheme = lightColorScheme(
    primary = Orange3,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    error = Color(0xFFB3261E),
    onError = Color.White
)

// Dark Color
private val DarkColorScheme = darkColorScheme(
    primary = Orange3,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    error = Color(0xFFF2B8B5),
    onError = Color.Black
)

//THEME STATE

class ThemeState(
    val isDarkTheme: Boolean,
    val toggleTheme: () -> Unit
)

@Composable
fun rememberThemeState(
    initialDarkTheme: Boolean = isSystemInDarkTheme()
): ThemeState {
    var isDarkTheme by remember { mutableStateOf(initialDarkTheme) }

    return ThemeState(
        isDarkTheme = isDarkTheme,
        toggleTheme = { isDarkTheme = !isDarkTheme }
    )
}

// MAIN THEME

@Composable
fun EcommerceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Status bar & navigation bar appearance
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }

    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

//HELPER EXTENSIONS

val MaterialTheme.isDark: Boolean
    @Composable get() = colorScheme.background == DarkColorScheme.background
