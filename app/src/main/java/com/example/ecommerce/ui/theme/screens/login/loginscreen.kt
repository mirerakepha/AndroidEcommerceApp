package com.example.ecommerce.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.SIGNUP_URL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()

        ,
        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row {
            Image(painter = painterResource(id = R.drawable.img) ,
                contentDescription = "",
                modifier = Modifier.size(100.dp))

            Text(
                text = "rit",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta,
                fontFamily = FontFamily.Cursive

            )
        }

        Text(
            text = "Your hostel companion",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "SIGN IN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )

        Text(
            text = "Get your hostel here!",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )






        Spacer(modifier = Modifier.height(30.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email Address")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "", tint = Color.Gray)}
        )

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "", tint = Color.Gray)}
        )

        Spacer(modifier = Modifier.height(30.dp))
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)

        Button(onClick = {
            authViewModel.login(email, password)
        },
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "If you don't have an account? Create an account ",
            fontSize = 15                                                                                                                                                                                                                                                                                                                                                               .sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(SIGNUP_URL)

                }

        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){

    LoginScreen(navController = rememberNavController())

}