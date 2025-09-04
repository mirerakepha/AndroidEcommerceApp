package com.example.ecommerce.ui.theme.screens.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.CHECKOUT_URL
import com.example.ecommerce.ui.theme.Orange3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart",

                    fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { CHECKOUT_URL },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange3,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Checkout",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        CartBody(Modifier.padding(innerPadding))
    }
}

@Composable
fun CartBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // === Cart Items ===
        CartItemRow("Gaming Mouse", "Logitech", 88.00, R.drawable.mouse)
        Spacer(modifier = Modifier.height(12.dp))
        CartItemRow("Gaming Chair", "Finex furnitures", 99.00, R.drawable.mouse)
        Spacer(modifier = Modifier.height(12.dp))
        CartItemRow("Gaming PC", "Custom Built RTX 5090, 18GB DDR5 RAM", 149.00, R.drawable.mouse)

        Spacer(modifier = Modifier.height(20.dp))

        // === Promo Code ===
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Apply Promo Code",
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Apply",
                color = Orange3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* TODO apply promo */ }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // === Totals ===
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TotalRow("Order Total", "$336")
            TotalRow("Offer", "-$99")
            TotalRow("Total", "$237", isBold = true)
        }
    }
}

@Composable
fun CartItemRow(title: String, subtitle: String, price: Double, imageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
            Text(text = "$${price}", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }

        // Quantity control
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO decrease */ }) {
                Text("-", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text("1", modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(onClick = { /* TODO increase */ }) {
                Text("+", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Orange3)
            }
        }
    }
}

@Composable
fun TotalRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontSize = if (isBold) 18.sp else 16.sp
        )
        Text(
            text = value,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontSize = if (isBold) 18.sp else 16.sp
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}
