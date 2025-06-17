package com.example.ecommerce.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.EcommerceTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val coroutine = rememberCoroutineScope()
        coroutine.launch {
            delay(2000)
            navController.navigate(Screen1_URL)
        }




    }


















    }





















@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}