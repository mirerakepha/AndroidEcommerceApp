package com.example.ecommerce.ui.theme.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.ui.theme.screens.home.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {

    var selected by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Notifications") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Orange3,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .shadow(8.dp, RoundedCornerShape(24.dp))
            ) {
                NavigationBar(
                    modifier = Modifier
                        .height(64.dp)
                        .background(Color.White),
                    tonalElevation = 0.dp,
                    containerColor = Color.White
                ) {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
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
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavItem.title,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        },
        containerColor = Orange3
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 70.dp), // Space for bottom bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(305.dp)
                    .clip(shape = RectangleShape)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    val items = listOf(
                        "Contact Us With Chat",
                        "Order Query(Available 24 hours)",
                        "System Message",
                        "Order Updates",
                        "Promotions",
                        "Comments"
                    )

                    items.forEach { label ->
                        Button(
                            onClick = { /* TODO */ },
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = label)

                                if (label == "Order Updates") {
                                    BadgedBox(badge = {
                                        Badge {
                                            Text("2", fontSize = 8.sp)
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(1.dp))
                    }

                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.LightGray)
            ) {
                Column {
                    Text(
                        text = "Chat With Sellers",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "You can view all your chats with sellers here",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
            }
        }
    }
}


val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Messages",
        route="add_complaint",
        selectedIcon=Icons.Filled.MailOutline,
        unselectedIcon=Icons.Outlined.MailOutline,
        hasNews = true,
        badges=0
    ),

    BottomNavItem(
        title = "Feed",
        route="feed",
        selectedIcon=Icons.Filled.MailOutline,
        unselectedIcon=Icons.Outlined.MailOutline,
        hasNews = false,
        badges=0
    ),

    BottomNavItem(
        title = "Account",
        route="account",
        selectedIcon= Icons.Filled.AccountCircle,
        unselectedIcon=Icons.Outlined.AccountCircle,
        hasNews = true,
        badges=1
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}
