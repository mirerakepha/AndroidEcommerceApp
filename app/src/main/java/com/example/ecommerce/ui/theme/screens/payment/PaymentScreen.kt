package com.example.ecommerce.ui.theme.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.Orange3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    totalAmount: Double
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopAppBar(
            title = { Text("Payment") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PaymentMethodsRow()

            Spacer(modifier = Modifier.height(50.dp))


            CreditCard(
                cardNumber = "**** **** **** 1234",
                cardHolder = "Jack of all Trades",
                expiryDate = "12/25",
                onClick = { /* Handle card click */ }
            )

            Spacer(modifier = Modifier.height(50.dp))


            Row(
                modifier = Modifier.width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total")
                Text(
                    text = "$ ${"%.2f".format(totalAmount)}",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange3,
                    contentColor = Color.White
                ),
                onClick = { /* Handle confirm order click */ },
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Confirm Order")
            }
        }
    }
}

@Composable
fun CreditCard(
    cardNumber: String,
    cardHolder: String,
    expiryDate: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(350.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFff7e5f), Color(0xFFfeb47b))
                )
            )
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Bank Card",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_chip),
                    contentDescription = "Chip",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(40.dp)
                )
            }

            // Card Number
            Text(
                text = cardNumber,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                letterSpacing = 2.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Card Holder", color = Color.LightGray, fontSize = 12.sp)
                    Text(cardHolder, color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Expires", color = Color.LightGray, fontSize = 12.sp)
                    Text(expiryDate, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(navController = rememberNavController(), totalAmount = 390.0)
}

@Composable
fun PaymentMethodsRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Payment Method",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaymentButton(
                text = "PayPal",
                logoResId = R.drawable.paypallogo,
                onClick = { /* Handle PayPal click */ }
            )

            PaymentButton(
                text = "Google",
                logoResId = R.drawable.googlelogo,
                onClick = { /* Handle Google Pay click */ }
            )

            PaymentButton(
                text = "Apple",
                logoResId = R.drawable.apple,
                onClick = { /* Handle Apple Pay click */ }
            )

            PaymentButton(
                text = "Visa",
                logoResId = R.drawable.visalogo,
                onClick = { /* Handle Visa click */ }
            )
        }
    }
}

@Composable
fun PaymentButton(
    text: String,
    logoResId: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(90.dp)
            .height(80.dp)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange3,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "$text logo",
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text, fontSize = 10.sp, textAlign = TextAlign.Center)
        }
    }
}
