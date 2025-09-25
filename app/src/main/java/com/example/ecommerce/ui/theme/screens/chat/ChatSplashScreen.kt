package com.example.ecommerce.ui.theme.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.INCHART_URL
import com.example.ecommerce.ui.theme.Orange3
import kotlinx.coroutines.delay

@Composable
fun ChatSplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(INCHART_URL){
            popUpTo("chat_splash"){inclusive = true}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.chat),
            contentDescription = "chat icon",
            colorFilter = ColorFilter.tint(Orange3),
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // App name
        Text(
            text = "Las Chat",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Orange3,
            fontFamily = FontFamily.Cursive
        )

    }






}



@Preview(showBackground = true)
@Composable
fun ChatSplashScreenPreview() {
    ChatSplashScreen(navController = rememberNavController())
}