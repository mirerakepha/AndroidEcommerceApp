package com.example.ecommerce.ui.theme.screens.products

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.Orange3

@Composable
fun ProductScreen(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf("M") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp, top = 20.dp)
        ) {
            // Image with overlay icons
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.itachih),
                    contentDescription = "",
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

            // Product Title & Price
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Uchiha clan Hoodie",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Men’s Outerwear",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "$ 99.00",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Size Selection
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
                    modifier = Modifier.clickable { /* Navigate to Size Chart */ }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                listOf("S", "M", "L", "XL", "XXL", "XXXL").forEach { size ->
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

            // Description
            Text(
                text = buildAnnotatedString {
                    append("Elevate your everyday look with this Casual Hoodie, designed for comfort and effortless style. Made from soft, breathable fabric... ")
                    withStyle(style = SpanStyle(color = Orange3, fontWeight = FontWeight.Bold)) {
                        append("Learn More")
                    }
                },
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Similar Products
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Similar products", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    "See All",
                    color = Orange3,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Navigate to all */ }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.itachih),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.itachih),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }

        // Bottom Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { /* Add to Cart */ },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Add to Cart", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* Buy Now */ },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange3)
            ) {
                Text("Buy Now", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductScreen(navController = rememberNavController())
}
