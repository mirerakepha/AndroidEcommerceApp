package com.example.ecommerce.ui.theme.screens.products

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.data.CartViewModel
import com.example.ecommerce.models.CartItem
import com.example.ecommerce.navigation.ORDERCONFIRMATION_URL
import com.example.ecommerce.ui.theme.Orange3

@Composable
fun ProductScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf("M") }

    // Example product (in real-world, pass from nav args or ViewModel)
    val product = CartItem(
        id = 1,
        title = "Casual Hoodie Brown",
        subtitle = "Men’s Outerwear",
        price = 99.0,
        imageRes = R.drawable.itachih,
        quantity = 1
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp) // space for bottom bar
        ) {
            // === Product Image + Buttons ===
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Back button
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                        .clickable { navController.popBackStack() }
                )

                // Favorite button
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.DarkGray,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd)
                        .clickable { isFavorite = !isFavorite }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // === Product Title & Price ===
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = product.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = product.subtitle,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "$ ${product.price}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // === Size Selection ===
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Select Size", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = "Size Chart",
                    color = Orange3,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* TODO: Navigate to Size Chart */ }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                listOf("S", "M", "L", "XL", "4XL").forEach { size ->
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = if (selectedSize == size) Orange3 else Color.Gray,
                                shape = CircleShape
                            )
                            .clickable { selectedSize = size },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = size,
                            fontWeight = FontWeight.SemiBold,
                            color = if (selectedSize == size) Orange3 else Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // === Description ===
            Text(
                text = buildAnnotatedString {
                    append("Elevate your everyday look with this Casual Hoodie, designed for comfort and effortless style... ")
                    withStyle(style = SpanStyle(color = Orange3, fontWeight = FontWeight.Bold)) {
                        append("Learn More")
                    }
                },
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // === Bottom Bar ===
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            OutlinedButton(
                onClick = {
                    cartViewModel.addToCart(product)
                    Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Add to Cart", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { ORDERCONFIRMATION_URL },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange3)
            ) {
                Text("Buy Now", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen(navController = rememberNavController())
}
