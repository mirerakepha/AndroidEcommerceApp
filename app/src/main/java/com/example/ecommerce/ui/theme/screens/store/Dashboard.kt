package com.example.ecommerce.ui.theme.screens.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.data.Product
import com.example.ecommerce.navigation.DASHBOARD_URL
import com.example.ecommerce.navigation.ORDERSMADE_URL
import com.example.ecommerce.navigation.SALESMADE_URL
import com.example.ecommerce.navigation.STOREPROFILE_URL
import com.example.ecommerce.ui.theme.Orange3
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    storeName: String,
    onAddProductClick: () -> Unit = {},
    products: List<Product> = emptyList()
) {
    var randomizedProducts by remember {
        mutableStateOf(products.shuffled(Random(System.currentTimeMillis())))
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = storeName,
                        color = Orange3,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProductClick, containerColor = Orange3) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                NavigationBarItem(
                    selected = currentRoute == DASHBOARD_URL,
                    onClick = { navController.navigate(DASHBOARD_URL) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") }
                )
                NavigationBarItem(
                    selected = currentRoute == ORDERSMADE_URL,
                    onClick = { navController.navigate(ORDERSMADE_URL) },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Orders") },
                    label = { Text("Orders") }
                )
                NavigationBarItem(
                    selected = currentRoute == SALESMADE_URL,
                    onClick = { navController.navigate(SALESMADE_URL) },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Sales") },
                    label = { Text("Sales") }
                )
                NavigationBarItem(
                    selected = currentRoute == STOREPROFILE_URL,
                    onClick = { navController.navigate(STOREPROFILE_URL) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Your Products",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (randomizedProducts.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No products available")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(randomizedProducts) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (product.imageUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = Orange3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val sampleProducts = listOf(
        Product(id = "1", name = "Shoes", description = "Running shoes", price = 49.99, category = "Shoes", imageUrl = ""),
        Product(id = "2", name = "T-shirt", description = "Cotton Tee", price = 19.99, category = "Clothing", imageUrl = ""),
        Product(id = "3", name = "Laptop", description = "Powerful Laptop", price = 999.99, category = "Electronics", imageUrl = "")
    )

    DashboardScreen(
        navController = rememberNavController(),
        storeName = "Finest Fibres",
        products = sampleProducts
    )
}
