package com.example.ecommerce.ui.theme.screens.store

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.navigation.HOME_URL
import com.example.ecommerce.navigation.LOGIN_URL
import com.example.ecommerce.navigation.PHONELOGIN_URL
import com.example.ecommerce.navigation.SIGNUP_URL
import com.example.ecommerce.navigation.STOREREGISTRATION_URL
import com.example.ecommerce.ui.theme.Orange3


@Composable
fun StoreLoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onOtpClick: () -> Unit

) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = painterResource(id = R.drawable.shopping),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )



        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "Welcome Back",
            fontSize = 40.sp,
            color = Orange3,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email Address", fontFamily = FontFamily.SansSerif) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        var passwordVisible by remember { mutableStateOf(false) }
        val visualTransformation: VisualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password", fontFamily = FontFamily.SansSerif) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(5.dp),
            visualTransformation = visualTransformation,
            trailingIcon = {
                val icon = if (passwordVisible) painterResource(id = R.drawable.pwds)
                else painterResource(id = R.drawable.pwdh)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = icon, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(Orange3),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)
        ) {
            Text("Login", fontFamily = FontFamily.SansSerif)
        }

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = "Don't have a store? Create",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { STOREREGISTRATION_URL }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Divider with OR
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color.Gray)
            Text(" OR ", color = Color.Gray, fontSize = 14.sp)
            Divider(modifier = Modifier.weight(1f), color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Google Sign-In
        OutlinedButton(
            onClick = onGoogleClick,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.googlelogo),
                contentDescription = "Google Sign-In",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with Google", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // OTP Sign-In
        OutlinedButton(
            onClick = { PHONELOGIN_URL },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pwds),
                contentDescription = "OTP Sign-In",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with OTP", color = Color.Black)
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun StoreLoginScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.initializeGoogleSignIn(context)
    }
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        authViewModel.handleGoogleSignInResult(result) { success, message ->
            if (success) {
                navController.navigate(HOME_URL) {
                    popUpTo(LOGIN_URL) { inclusive = true }
                }
            } else {
                println("Google sign-in failed: $message")
            }
        }
    }

    StoreLoginScreenContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            authViewModel.storeLogin(email, password) { success, message ->
                if (success) {
                    navController.navigate("dashboard")
                } else {
                    println("Login failed: $message")
                }
            }
        },
        onGoogleClick = {val signInIntent = authViewModel.getGoogleSignInIntent()
                googleSignInLauncher.launch(signInIntent)
        },
        onOtpClick = {
            navController.navigate("phone_login")
        }
    )

}


@Preview(showBackground = true)
@Composable
fun StoreLoginScreenPreview() {
    StoreLoginScreenContent(
        email = "batman@gmail.com",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onGoogleClick = {},
        onOtpClick = {}

    )
}
