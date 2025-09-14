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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import androidx.compose.ui.viewinterop.AndroidView

data class Sale(
    val id: String,
    val date: String, // For simplicity, just a string (e.g., "2025-09-10")
    val amount: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesMadeScreen(
    navController: NavController,
    storeName: String,
    sales: List<Sale> = sampleSales()
) {
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

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                factory = { context ->
                    LineChart(context).apply {
                        val entries = sales.mapIndexed { index, sale ->
                            Entry(index.toFloat(), sale.amount.toFloat())
                        }
                        val dataSet = LineDataSet(entries, "Sales").apply {
                            color = Color.BLUE
                            valueTextColor = Color.BLACK
                            lineWidth = 2f
                            setCircleColor(Color.RED)
                        }
                        this.data = LineData(dataSet)

                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        axisRight.isEnabled = false
                        description.isEnabled = false
                        invalidate()
                    }
                }
            )
        }
    }
}

fun sampleSales(): List<Sale> = listOf(
    Sale("S001", "2025-09-01", 250.0),
    Sale("S002", "2025-09-02", 500.0),
    Sale("S003", "2025-09-03", 120.0),
    Sale("S004", "2025-09-04", 700.0)
)

@Preview(showBackground = true)
@Composable
fun PreviewSalesMadeScreen() {
    val navController = rememberNavController()
    SalesMadeScreen(
        navController = navController,
        storeName = "Finest Fibres",
        sales = sampleSales()
    )
}
