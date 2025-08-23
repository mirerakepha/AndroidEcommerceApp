package com.example.ecommerce.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.LOGIN_URL
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.ui.theme.Orange3

@Composable
fun SignupScreenContent(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.shopping),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = "LAS NOCHES",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Orange3,
            fontFamily = FontFamily.Cursive
        )

        Text(
            text = "SAVES MORE",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "CREATE AN ACCOUNT",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Username") },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.Gray)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = Color.Gray)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Color.Gray)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(Orange3),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Already have an account? Login",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLoginClick() }
        )
    }
}

@Composable
fun SignupScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    SignupScreenContent(
        name = name,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        onNameChange = { name = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onRegisterClick = {
            authViewModel.signup(name, email, password, confirmPassword)
        },
        onLoginClick = {
            navController.navigate(LOGIN_URL)
        }
    )
}




@Composable
fun SignupScreen(navController: NavHostController) {
    val context = LocalContext.current
    val vm = remember(navController, context) { AuthViewModel(navController, context) }
    SignupScreen(navController = navController, authViewModel = vm)
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreenContent(
        name = "Kepha Mirera",
        email = "kephamirera@.com",
        password = "12345678",
        confirmPassword = "12345678",
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}
