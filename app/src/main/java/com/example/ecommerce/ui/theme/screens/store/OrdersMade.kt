package com.example.ecommerce.ui.theme.screens.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3

data class Order(
    val id: String,
    val customerName: String,
    val productName: String,
    val status: String // Pending or Delivered
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersMadeScreen(
    navController: NavController,
    orders: List<Order> = sampleOrders()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Orders Made", color = Orange3) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Orange3
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Pending Orders", style = MaterialTheme.typography.titleMedium, color = Orange3)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orders.filter { it.status == "Pending" }) { order ->
                    OrderCard(order)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Delivered Orders", style = MaterialTheme.typography.titleMedium, color = Orange3)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orders.filter { it.status == "Delivered" }) { order ->
                    OrderCard(order)
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Order ID: ${order.id}", style = MaterialTheme.typography.bodyMedium)
            Text("Customer: ${order.customerName}")
            Text("Product: ${order.productName}")
            Text(
                "Status: ${order.status}",
                color = if (order.status == "Pending")
                    MaterialTheme.colorScheme.error
                else
                    Orange3
            )
        }
    }
}

fun sampleOrders(): List<Order> = listOf(
    Order("001", "Alice", "Shoes", "Pending"),
    Order("002", "Bob", "Laptop", "Delivered"),
    Order("003", "Chris", "Watch", "Pending"),
    Order("004", "Diana", "Bag", "Delivered")
)

@Preview(showBackground = true)
@Composable
fun PreviewOrdersMadeScreen() {
    val navController = rememberNavController()
    OrdersMadeScreen(
        navController = navController,
        orders = sampleOrders()
    )
}
