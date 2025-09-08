package com.example.ecommerce.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.LOGIN_URL
import com.example.ecommerce.navigation.HOME_URL
import com.example.ecommerce.ui.theme.Orange3
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavController,
    isUserLoggedIn: () -> Boolean = { false } // pass in your auth check here
) {
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(2000)
        if (isUserLoggedIn()) {
            navController.navigate(HOME_URL) {
                popUpTo(0) // clear backstack so splash isn’t revisit-able
            }
        } else {
            navController.navigate(LOGIN_URL) {
                popUpTo(0)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.shopping),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // App name
        Text(
            text = "Las Noches",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Orange3,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
