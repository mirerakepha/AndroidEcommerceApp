package com.example.ecommerce.ui.theme.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.ui.theme.Orange3


@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Welcome Back",
            fontSize = 40.sp,
            color = Orange3,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(20.dp))

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
            text = "Dont have an account? Sign up",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLoginClick() }
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginScreenContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            authViewModel.login(email, password)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        email = "kephamirera@gmail.com",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}
