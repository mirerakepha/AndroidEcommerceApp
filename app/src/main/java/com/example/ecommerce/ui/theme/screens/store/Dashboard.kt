package com.example.ecommerce.ui.theme.screens.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardScreen(
    navController: NavController,
    storeName: String,
    onAddProductClick: () -> Unit = {} // default no-op
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Welcome, $storeName!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Add Product Button
        Button(
            onClick = { onAddProductClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Product")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section Title
        Text(
            text = "Your Products:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Placeholder list of products (replace with ViewModel data later)
        val sampleProducts = listOf("Shoes", "T-shirt", "Laptop")

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(sampleProducts) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = product,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        navController = rememberNavController(),
        storeName = "Demo Store"
    )
}
