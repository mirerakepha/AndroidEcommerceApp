package com.example.ecommerce.ui.theme.screens.store

import androidx.compose.foundation.background
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
import com.example.ecommerce.models.Order
import com.example.ecommerce.ui.theme.Orange3

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
                .background(MaterialTheme.colorScheme.background )
                .padding(16.dp)
        ) {
            // Pending Orders
            Text("Pending Orders", style = MaterialTheme.typography.titleMedium, color = Orange3)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orders.filter { it.status.equals("pending", ignoreCase = true) }) { order ->
                    OrderCard(order)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Delivered / Shipped Orders
            Text("Signed-off Orders", style = MaterialTheme.typography.titleMedium, color = Orange3)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orders.filter { it.status.equals("shipped", ignoreCase = true) }) { order ->
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
            Text("Buyer ID: ${order.buyerId}")
            Text("Product ID: ${order.productId}")
            Text("Quantity: ${order.quantity}")
            Text("Total Price: $${order.totalPrice}")
            Text(
                "Status: ${order.status}",
                color = if (order.status.equals("pending", ignoreCase = true))
                    MaterialTheme.colorScheme.error
                else
                    Orange3
            )
        }
    }
}

// Temporary fake data for preview
fun sampleOrders(): List<Order> = listOf(
    Order(id = "001", productId = "P001", buyerId = "B001", storeId = "S001", quantity = 2, totalPrice = 200.0, status = "pending"),
    Order(id = "002", productId = "P002", buyerId = "B002", storeId = "S001", quantity = 1, totalPrice = 1200.0, status = "shipped"),
    Order(id = "003", productId = "P003", buyerId = "B003", storeId = "S001", quantity = 3, totalPrice = 90.0, status = "pending"),
    Order(id = "004", productId = "P004", buyerId = "B004", storeId = "S001", quantity = 1, totalPrice = 450.0, status = "shipped")
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
