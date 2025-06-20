package com.example.ecommerce.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import com.example.ecommerce.R
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
import com.example.ecommerce.navigation.LOGIN_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



//LOGO
        

            Image(painter = painterResource(id = R.drawable.shopping) ,
                contentDescription = "",
                modifier = Modifier.size(100.dp))

            Text(
                text = "LAS NOCHES",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta,
                fontFamily = FontFamily.Cursive

            )


        Text(
            text = "SAVES MORE",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "CREATE AN ACCOUNT",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )




        Spacer(modifier = Modifier.height(30.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text(text = "Username")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "", tint = Color.Gray)}

        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = " Email Address ")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "", tint = Color.Gray)}

        )

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = " User Password")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "", tint = Color.Gray)}

        )

        Spacer(modifier = Modifier.height(30.dp))

        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(onClick = {
            authViewModel.signup(name, email, password)
        },
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp)) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text = "If you already have an account? Login ",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    navController.navigate(LOGIN_URL)
                }
        )

    }
}

@Composable
@Preview(showBackground = true)
fun SignupScreenPreview(){

    SignupScreen(navController = rememberNavController())

}