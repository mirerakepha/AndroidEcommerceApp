package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.screens.signup.SignupScreen
import com.example.ecommerce.ui.theme.screens.account.AccountScreen
import com.example.ecommerce.ui.theme.screens.cart.CartScreen
import com.example.ecommerce.ui.theme.screens.checkout.CheckoutScreen
import com.example.ecommerce.ui.theme.screens.home.HomeScreen
import com.example.ecommerce.ui.theme.screens.login.LoginScreen
import com.example.ecommerce.ui.theme.screens.notifications.NotificationScreen
import com.example.ecommerce.ui.theme.screens.orderconfirmation.OrderConfirmationScreen
import com.example.ecommerce.ui.theme.screens.orderdetails.OrderdetailsScreen
import com.example.ecommerce.ui.theme.screens.payment.PaymentScreen
import com.example.ecommerce.ui.theme.screens.productdetails.ProductdetailsScreen
import com.example.ecommerce.ui.theme.screens.products.ProductScreen
import com.example.ecommerce.ui.theme.screens.profile.ProfileScreen
import com.example.ecommerce.ui.theme.screens.settings.SettingScreen
import com.example.ecommerce.screens.store.StoreRegistrationScreen
import com.example.ecommerce.ui.theme.screens.auth.OtpScreen
import com.example.ecommerce.ui.theme.screens.auth.PhoneLoginScreen
import com.example.ecommerce.ui.theme.screens.custstore.CustStoreScreen
import com.example.ecommerce.ui.theme.screens.splash.SplashScreen
import com.example.ecommerce.ui.theme.screens.store.AddProductScreen
import com.example.ecommerce.ui.theme.screens.store.DashboardScreen
import com.example.ecommerce.ui.theme.screens.store.OrdersMadeScreen
import com.example.ecommerce.ui.theme.screens.store.SalesMadeScreen
import com.example.ecommerce.ui.theme.screens.store.StoreLoginScreen
import com.example.ecommerce.ui.theme.screens.store.StoreProfileScreen
import com.example.ecommerce.models.Store
import com.example.ecommerce.models.Rating

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPLASH_URL
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(LOGIN_URL) {
            LoginScreen(navController = navController, authViewModel = TODO())
        }
        composable(SIGNUP_URL) {
            SignupScreen(navController = navController)
        }
        composable(SPLASH_URL) {
            SplashScreen(navController = navController)
        }
        composable(SETTINGS_URL) {
            SettingScreen(navController = navController, themeState = TODO())
        }
        composable(ORDERDETAILS_URL) {
            OrderdetailsScreen(navController = navController)
        }
        composable(ORDERCONFIRMATION_URL) {
            OrderConfirmationScreen(navController = navController)
        }
        composable(PAYMENT_URL) {
            PaymentScreen(navController = navController, totalAmount = 390.0)
        }
        composable(PROFILE_URL) {
            ProfileScreen(navController = navController)
        }
        composable(OTP_URL) {
            OtpScreen(
                navController = navController,
                onVerifyOtp = { otp ->
                    // Handle OTP verification
                },
                onResendOtp = {
                    // Handle resend
                }
            )
        }
        composable(CART_URL) {
            CartScreen(navController = navController)
        }
        composable(HOME_URL) {
            HomeScreen(navController = navController)
        }
        composable(CHECKOUT_URL) {
            CheckoutScreen(navController = navController)
        }
        composable(PRODUCTS_URL) {
            ProductScreen(navController = navController)
        }
        composable(PRODUCTDETAILS_URL) {
            ProductdetailsScreen(navController = navController)
        }
        composable(ACCOUNT_URL) {
            AccountScreen(navController = navController)
        }
        composable(NOTIFICATIONS_URL) {
            NotificationScreen(navController = navController)
        }
        composable(PHONELOGIN_URL) {
            PhoneLoginScreen(
                navController = navController,
                onSendOtp = { phoneOrEmail ->
                    // Call Firebase / API to send OTP
                }
            )
        }
        composable(DASHBOARD_URL) {
            DashboardScreen(
                navController = navController,
                storeName = "My Store",
                onAddProductClick = {
                    navController.navigate(ADDPRODUCT_URL)
                }
            )
        }
        composable(STOREREGISTRATION_URL) {
            StoreRegistrationScreen(navController = navController)
        }
        composable(ADDPRODUCT_URL) {
            AddProductScreen(navController = navController)
        }
        composable(STORELOGIN_URL) {
            StoreLoginScreen(navController = navController, authViewModel = TODO())
        }
        composable(ORDERSMADE_URL) {
            OrdersMadeScreen(navController = navController)
        }
        composable(SALESMADE_URL) {
            SalesMadeScreen(
                navController = navController,
                storeName = "Finest Fibres",
                orders = TODO()
            )
        }
        composable(STOREPROFILE_URL) {
            StoreProfileScreen(navController = navController, store = TODO())
        }

        //Customer store screen with storeId argument
        composable("$CUSTSTORE_URL/{storeId}") { backStackEntry ->
            val storeId = backStackEntry.arguments?.getString("storeId") ?: return@composable

            // Sample data until you fetch from backend
            val sampleStore = Store(
                id = storeId,
                name = "Finest Fibres",
                bannerUrl = "",
                location = "Nairobi",
                address = "123 Market Street",
                contactInfo = "+254 712 345678",
                zipcode = "00100",
                description = "We specialize in premium quality fabrics and fibers."
            )

            val sampleRatings = listOf(
                Rating(userId = "u1", storeId = storeId, value = 4.0f),
                Rating(userId = "u2", storeId = storeId, value = 5.0f),
                Rating(userId = "u3", storeId = storeId, value = 3.5f)
            )

            CustStoreScreen(
                navController = navController,
                store = sampleStore,
                ratings = sampleRatings,
                isUserLoggedIn = true, // Replace with real AuthViewModel check
                onRateStore = { rating ->
                    println("User rated store $storeId with $rating stars")
                }
            )
        }
    }
}
