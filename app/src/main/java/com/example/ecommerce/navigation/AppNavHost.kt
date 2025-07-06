package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.screens.account.AccountScreen
import com.example.ecommerce.ui.theme.screens.cart.CartScreen
import com.example.ecommerce.ui.theme.screens.checkout.CheckoutScreen
import com.example.ecommerce.ui.theme.screens.home.HomeScreen
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import com.example.ecommerce.ui.theme.screens.notifications.NotificationScreen
import com.example.ecommerce.ui.theme.screens.orderconfirmation.OrderconfirmationScreen
import com.example.ecommerce.ui.theme.screens.orderdetails.OrderdetailsScreen
import com.example.ecommerce.ui.theme.screens.payment.PaymentScreen
import com.example.ecommerce.ui.theme.screens.productdetails.ProductdetailsScreen
import com.example.ecommerce.ui.theme.screens.products.ProductScreen
import com.example.ecommerce.ui.theme.screens.profile.ProfileScreen
import com.example.ecommerce.ui.theme.screens.settings.SettingScreen
import com.example.ecommerce.ui.theme.screens.signup.SignupScreen

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
            SignupScreen(navController = navController)
        }
        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }
        composable(SETTINGS_URL){
            SettingScreen(navController = navController)
        }
        composable(ORDERDETAILS_URL){
            OrderdetailsScreen(navController = navController)
        }
        composable(ORDERCONFIRMATION_URL){
            OrderconfirmationScreen(navController = navController)
        }
        composable(PAYMENT_URL){
            PaymentScreen(navController = navController)
        }
        composable(PROFILE_URL){
            ProfileScreen(navController = navController)
        }
        composable(CART_URL){
            CartScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(CHECKOUT_URL){
            CheckoutScreen(navController = navController)
        }
        composable(PRODUCTS_URL){
            ProductScreen(navController = navController)
        }
        composable(PRODUCTDETAILS_URL){
            ProductdetailsScreen(navController = navController)
        }
        composable(ACCOUNT_URL){
            AccountScreen(navController = navController)
        }
        composable(NOTIFICATIONS_URL){
            NotificationScreen(navController = navController)
        }

    }
}