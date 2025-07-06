package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.ThemeState
import com.example.ecommerce.ui.theme.rememberThemeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val themeState = rememberThemeState()

            EcommerceTheme(darkTheme = themeState.isDarkTheme) {
                AppContent(themeState)
            }


        }
    }
}

private fun MainActivity.AppContent(state: ThemeState) {}

