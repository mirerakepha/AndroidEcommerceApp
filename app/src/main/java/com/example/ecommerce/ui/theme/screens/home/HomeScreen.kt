package com.example.ecommerce.ui.theme.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.navigation.CART_URL
import com.example.ecommerce.navigation.NOTIFICATIONS_URL
import com.example.ecommerce.navigation.PRODUCTDETAILS_URL
import com.example.ecommerce.navigation.SETTINGS_URL
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.R
import com.example.ecommerce.navigation.ORDERDETAILS_URL
import com.example.ecommerce.data.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.tasks.await

data class Screen(val title: String, val icon: Int)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var selected by remember { mutableIntStateOf(0) }
    var search by remember { mutableStateOf("") }
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    var filteredProducts by remember { mutableStateOf<List<Product>>(emptyList()) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Fetch products from Firestore
    LaunchedEffect(Unit) {
        try {
            val db = Firebase.firestore
            val productList = mutableListOf<Product>()
            val querySnapshot = db.collection("products").get().await()
            for (document in querySnapshot) {
                val product = document.toObject(Product::class.java)
                productList.add(product)
            }
            products = productList
            filteredProducts = productList
        } catch (e: Exception) {
            // Handle error
        }
    }

    // Filter products based on search and category
    LaunchedEffect(search, selectedCategory, products) {
        filteredProducts = products.filter { product ->
            val matchesSearch = search.isEmpty() ||
                    product.name.contains(search, ignoreCase = true) ||
                    product.description.contains(search, ignoreCase = true) ||
                    (product.category?.contains(search, ignoreCase = true) == true)

            val matchesCategory = selectedCategory == null ||
                    product.category.equals(selectedCategory, ignoreCase = true)

            matchesSearch && matchesCategory
        }
    }


    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.DarkGray)
                    .shadow(8.dp, RoundedCornerShape(24.dp))
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBar(
                    modifier = Modifier
                        .height(64.dp)
                        .background(Color.DarkGray),
                    tonalElevation = 0.dp,
                    containerColor = Orange3
                ) {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = currentRoute == "home",
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(
                                                    text = bottomNavItem.badges.toString(),
                                                    fontSize = 8.sp
                                                )
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selected)
                                            bottomNavItem.selectedIcon
                                        else
                                            bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title,
                                        modifier = Modifier.size(20.dp),
                                        tint = Color.White
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavItem.title,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                            },
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(CART_URL) }) {
                IconButton(onClick = { navController.navigate(CART_URL) }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "cart"
                    )
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Las Noches") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(SETTINGS_URL) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "settings",
                            tint = Orange3
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(NOTIFICATIONS_URL) }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "notification icon",
                            tint = Orange3
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // SEARCH BAR
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text(text = "find products") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .focusRequester(focusRequester)
                        .clickable {
                            keyboardController?.show()
                            focusRequester.requestFocus()
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(30.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Show search results if searching
                if (search.isNotEmpty()) {
                    Text(
                        text = "Search Results for \"$search\"",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    ProductGrid(products = filteredProducts, navController = navController)
                } else {
                    // CATEGORY ROW
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        val categories = listOf(
                            "Explore", "Back to school", "Women's", "Men's",
                            "Electronics", "Toys", "Tools", "Jewellery", "Shoes", "Health"
                        )

                        categories.forEach { category ->
                            Text(
                                text = category,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textDecoration = if (selectedCategory == category) TextDecoration.Underline else null,
                                modifier = Modifier
                                    .clickable {
                                        selectedCategory = if (selectedCategory == category) null else category
                                        focusManager.clearFocus()
                                    }
                                    .padding(horizontal = 4.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    // Show products by category or all products
                    if (selectedCategory != null) {
                        Text(
                            text = "$selectedCategory Products",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        Text(
                            text = "Featured Products",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    ProductGrid(products = filteredProducts, navController = navController)
                }
            }
        }
    )
}

@Composable
fun ProductGrid(products: List<Product>, navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        if (products.isEmpty()) {
            Text(
                text = "No products found",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            products.chunked(2).forEach { rowProducts ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowProducts.forEach { product ->
                        ProductCard(product = product, navController = navController)
                    }
                    // Add empty space if odd number of products
                    if (rowProducts.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable {
                // Navigate to product details with product ID
                navController.navigate("${PRODUCTDETAILS_URL}/${product.id}")
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (product.imageUrl.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(product.imageUrl),
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Placeholder",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Text(
                text = product.name,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Orange3,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$${product.price}",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Orange3,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(
        title = "Messages",
        route = "add_complaint",
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline,
        hasNews = true,
        badges = 2
    ),
    BottomNavItem(
        title = "Orders",
        route = "orderdetails",
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline,
        hasNews = true,
        badges = 0
    ),
    BottomNavItem(
        title = "Account",
        route = "account",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
        badges = 0
    )
)

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badges: Int
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}