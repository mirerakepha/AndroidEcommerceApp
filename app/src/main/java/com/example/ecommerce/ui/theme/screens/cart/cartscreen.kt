package com.example.ecommerce.ui.theme.screens.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.ui.theme.Purple40
import com.example.ecommerce.ui.theme.screens.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {


    val context = LocalContext.current

    TopAppBar(title = {Text(text = "Cart")},
        navigationIcon = {
            IconButton(onClick = {Toast.makeText(context, "cart", Toast.LENGTH_SHORT).show()} ) {
                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = "cart")
            }
        }, colors = topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Orange3
        )


    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange3),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



    }






}



@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}