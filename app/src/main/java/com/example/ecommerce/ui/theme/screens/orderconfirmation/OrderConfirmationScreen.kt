package com.example.ecommerce.ui.theme.screens.orderconfirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(navController: NavController) {
    val walletSelected = remember { mutableStateOf(true) }
    val localDispatchSelected = remember { mutableStateOf(false) }
    val voucherUsed = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Place Order") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.popBackStack() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Orange3,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { /* Handle order placement */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Orange3)
            ) {
                Text("KSh 328 - Place Order", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            // Address Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Address",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Kepha Mirera 19387373",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "RURINGU Bomas dedan kimathi university adison street stall 2 acetech entertainment next to urban wish hotel 30mtrs from mzto supermarket, Cellphone:790235145Nyeri, Tetu, Dedan Kimanthi",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Delivery to this address is temporarily not supported. Please update or choose another address.",
                        fontSize = 14.sp,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Change Address",
                        color = Orange3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* Handle address change */ }
                    )
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // Payment Method Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Payment Method",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = { },
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("100% Money Back Guarantee", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Wallet option
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = walletSelected.value,
                        onCheckedChange = { walletSelected.value = it },
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Wallet",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Wallet", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Other payment options
                listOf(
                    "Via OKAVA KENYA LTD",
                    "+254 | Enter your M-PESA No.",
                    "Ask a friend to help pay",
                    "More Payment Options ✓"
                ).forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { },
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // LivShop Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "LivShop",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = localDispatchSelected.value,
                        onCheckedChange = { localDispatchSelected.value = it },
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Local Dispatch", fontSize = 14.sp)
                }

                Text(
                    "Ships from Spokimaur/Molongo, arrives in Dedan Kimanthi within 3-9 workdays.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 28.dp, top = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Product item
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Magic Cube multicoloured", fontWeight = FontWeight.Bold)
                        Text("multicoloured", color = Color.Gray, fontSize = 14.sp)
                        Text("x 1", color = Color.Gray, fontSize = 14.sp)
                    }
                    Text("KSh 199", fontWeight = FontWeight.Bold)
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // Discount Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Discount",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text("1 Coins To Be Redeemed", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = voucherUsed.value,
                        onCheckedChange = { voucherUsed.value = it },
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Vouchers Used", fontSize = 14.sp)
                }

                Text("0 available", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 28.dp))
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // Total Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Total",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Products Amount:", fontSize = 14.sp)
                    Text("KSh 199", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Shipping Fee:", fontSize = 14.sp)
                    Text("KSh 129", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = Color.Gray, thickness = 1.dp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Payment Amount:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("KSh 328", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Orange3)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmationScreenPreview() {
    OrderConfirmationScreen(navController = rememberNavController())
}