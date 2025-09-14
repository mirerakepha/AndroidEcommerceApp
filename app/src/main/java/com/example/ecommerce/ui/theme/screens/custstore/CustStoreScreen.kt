package com.example.ecommerce.ui.theme.screens.custstore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.models.Store
import com.example.ecommerce.models.Rating
import com.example.ecommerce.ui.theme.Orange3
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustStoreScreen(
    navController: NavController,
    store: Store,
    ratings: List<Rating>,
    isUserLoggedIn: Boolean,
    onRateStore: (Float) -> Unit
) {
    // Calculate average rating
    val avgRating = if (ratings.isNotEmpty()) {
        ratings.map { it.value }.average().toFloat()
    } else 0f

    var userRating by remember { mutableStateOf(0f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(store.name, color = Orange3) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Orange3)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            // Banner
            if (store.bannerUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(store.bannerUrl),
                    contentDescription = "${store.name} banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // quarter of screen approx
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Store name
            Text(
                text = store.name,
                style = MaterialTheme.typography.headlineSmall,
                color = Orange3,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ratings summary
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = if (index < avgRating.roundToInt()) Orange3 else MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.3f
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (ratings.isEmpty()) "No ratings yet"
                    else "${String.format("%.1f", avgRating)} (${ratings.size} ratings)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Store details
            InfoRow(label = "Location", value = store.location)
            InfoRow(label = "Address", value = store.address)
            InfoRow(label = "Zip Code", value = store.zipcode)
            InfoRow(label = "Contact", value = store.contactInfo)

            Spacer(modifier = Modifier.height(16.dp))

            // About
            if (store.description.isNotEmpty()) {
                Text(
                    text = "About Store",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = store.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Rate store section (only if logged in)
            if (isUserLoggedIn) {
                Text(
                    text = "Rate this store",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        IconButton(onClick = { userRating = (index + 1).toFloat() }) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rate ${index + 1}",
                                tint = if (index < userRating) Orange3 else MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.3f
                                )
                            )
                        }
                    }
                }

                Button(
                    onClick = { if (userRating > 0f) onRateStore(userRating) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange3)
                ) {
                    Text("Submit Rating")
                }
            } else {
                Text(
                    text = "Login to rate this store",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    if (value.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyLarge,
                color = Orange3,
                modifier = Modifier.width(90.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustStoreScreenPreview() {
    val sampleStore = Store(
        id = "store1",
        name = "Finest Fibres",
        bannerUrl = "",
        location = "Nairobi",
        address = "123 Market Street",
        contactInfo = "+254 712 345678",
        zipcode = "00100",
        description = "We specialize in premium quality fabrics and fibers. Trusted by thousands of happy customers."
    )

    val sampleRatings = listOf(
        Rating(userId = "u1", storeId = "store1", value = 4f),
        Rating(userId = "u2", storeId = "store1", value = 5f),
        Rating(userId = "u3", storeId = "store1", value = 3.5f),
    )

    CustStoreScreen(
        navController = rememberNavController(),
        store = sampleStore,
        ratings = sampleRatings,
        isUserLoggedIn = true,
        onRateStore = { rating -> println("Rated store with $rating stars") }
    )
}
