package com.example.ecommerce.ui.theme.screens.store

import android.graphics.Color
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.models.Order
import com.example.ecommerce.ui.theme.Orange3
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

// Data class representing a sale
data class Sale(
    val id: String,
    val date: String,
    val amount: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesMadeScreen(
    navController: NavController,
    storeName: String,
    orders: List<Order>
) {
    // Filter delivered orders and convert to sales
    val sales = ordersToSales(orders)
    val totalAmount = sales.sumOf { it.amount }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$storeName Sales", color = Orange3) },
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
            Text(
                text = "Total Sales: $$totalAmount",
                style = MaterialTheme.typography.headlineSmall,
                color = Orange3
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Sales Breakdown", style = MaterialTheme.typography.titleMedium)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(sales) { sale ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Sale ID: ${sale.id}")
                            Text("Date: ${sale.date}")
                            Text("Amount: $${sale.amount}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Sales Over Time", style = MaterialTheme.typography.titleMedium)

            if (sales.isNotEmpty()) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(vertical = 8.dp),
                    factory = { context ->
                        LineChart(context).apply {
                            val entries = sales.mapIndexed { index, sale ->
                                Entry(index.toFloat(), sale.amount.toFloat())
                            }

                            val dataSet = LineDataSet(entries, "Sales").apply {
                                color = Color.GREEN
                                valueTextColor = Color.BLACK
                                lineWidth = 2f
                                setCircleColor(Color.RED)
                                circleRadius = 3f
                                setDrawValues(true)
                            }

                            this.data = LineData(dataSet)

                            xAxis.position = XAxis.XAxisPosition.BOTTOM
                            xAxis.granularity = 1f
                            xAxis.valueFormatter = IndexAxisValueFormatter(sales.map { it.date })
                            axisRight.isEnabled = false
                            description.isEnabled = false
                            legend.isEnabled = true

                            invalidate()
                        }
                    }
                )
            } else {
                Text(
                    "No sales data available",
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}

// Convert delivered orders into sales
fun ordersToSales(orders: List<Order>): List<Sale> {
    return orders.filter { it.status.equals("delivered", ignoreCase = true) }
        .map { order ->
            Sale(
                id = "SALE-${order.id}",
                date = order.createdAt.toString(), // You can format timestamps if needed
                amount = order.totalPrice
            )
        }
}

// Sample orders for preview
fun sampleOrdersForSales(): List<Order> = listOf(
    Order(
        id = "001",
        productId = "P001",
        buyerId = "B001",
        storeId = "S001",
        quantity = 2,
        totalPrice = 500.0,
        status = "delivered"
    ),
    Order(
        id = "002",
        productId = "P002",
        buyerId = "B002",
        storeId = "S001",
        quantity = 1,
        totalPrice = 300.0,
        status = "pending"
    ),
    Order(
        id = "003",
        productId = "P003",
        buyerId = "B003",
        storeId = "S001",
        quantity = 1,
        totalPrice = 200.0,
        status = "delivered"
    )
)

@Preview(showBackground = true)
@Composable
fun PreviewSalesMadeScreen() {
    val navController = rememberNavController()
    SalesMadeScreen(
        navController = navController,
        storeName = "Finest Fibres",
        orders = sampleOrdersForSales()
    )
}
