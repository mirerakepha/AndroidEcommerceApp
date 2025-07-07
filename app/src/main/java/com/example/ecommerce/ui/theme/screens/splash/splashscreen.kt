package com.example.ecommerce.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {

    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        delay(2000)
        navController.navigate(LOGIN_URL)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {





            //image
            Image(painter = painterResource(id = R.drawable.shopping) ,
                contentDescription = "",
                modifier = Modifier.size(100.dp))
            
            //text

        Spacer(modifier = Modifier.height(20.dp))

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