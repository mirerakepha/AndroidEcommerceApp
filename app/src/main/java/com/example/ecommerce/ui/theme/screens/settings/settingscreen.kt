package com.example.ecommerce.ui.theme.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3

@Composable
fun SettingScreen(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {







    }






}



@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}