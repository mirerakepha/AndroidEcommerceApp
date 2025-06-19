package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import com.example.ecommerce.ui.theme.screens.login.login
import com.example.ecommerce.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            signupscreen(navController = navController)
        }
        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }


        composable(ORDERDETAILS_URL){
            orderdetailsscreen(navController = navController)
        }

        composable(ORDERCONFIRMATION_URL){
            orderconfirmationscreen(navController = navController)
        }

        composable(PAYMENT_URL){
            paymentscreen(navController = navController)
        }
        composable(PROFILE_URL){
            profilescreen(navController = navController)
        }
        composable(CART_URL){
            cartscreen(navController = navController)
        }
        composable(HOME_URL){
            homescreen(navController = navController)
        }
        composable(CHECKOUT_URL){
            checkoutscreen(navController = navController)
        }
        composable(PRODUCTS_URL){
            productscreen(navController = navController)
        }
        composable(PRODUCTDETAILS_URL){
            productdetailsscreen(navController = navController)
        }

    }
}