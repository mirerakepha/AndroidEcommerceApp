package com.example.ecommerce.ui.theme.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
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
Box(modifier = Modifier.fillMaxSize()){
    Column(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .fillMaxSize()
            .background(color = Orange3)
            .verticalScroll(rememberScrollState())
    ) {

        // Image with overlay icons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.itachih),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite) Color.Red else Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .clickable {
                        isFavorite = !isFavorite
                    }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Vintage Square Dial Leather Belt Wristwatch Brand Quatz Watch Casual Men Women",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            repeat(5) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "$ 15",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Reviews
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.LightGray)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
            //top of reviews
            Button(
                onClick = {/**/ },
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth().height(40.dp)
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left-aligned content (Icon + Text)
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(text = "Customer reviews (34)")
                    }

                    // Right-aligned arrow icon
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(2.dp))

            //reviewer 1
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "anonymous user")
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                Image(painterResource(id = R.drawable.itachih),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                        .padding(start = 5.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "anonymous user")
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                Image(painterResource(id = R.drawable.itachih),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                        .padding(start = 5.dp)
                )



            }
        }









        }
    }
    //bottom bar
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* Add to Cart */ },
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
        ) {
            Text("Add to Cart")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = { /* Buy Now */ },
            modifier = Modifier
                .weight(1f)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Orange3)
        ) {
            Text("Buy Now")
        }
    }
}}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductScreen(navController = rememberNavController())
}
