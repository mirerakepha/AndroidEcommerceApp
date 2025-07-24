package com.example.ecommerce.ui.theme.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


val CustomPurple = Color(0xFF673AB7)
val CustomOrange = Color(0xFFFF9800)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomPurple,
                    titleContentColor = CustomOrange,
                    navigationIconContentColor = Color.White
                )
            )
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .background(color = Color.Black)
                    .padding(horizontal = 15.dp), //
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Take full width within horizontal padding
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            width = 1.dp,
                            color = Color.Magenta,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Profiles Button
                        SettingsButton(
                            text = "Profiles",
                            icon = Icons.Default.Person,
                            onClick = { /* Handle profiles click */ },
                            showBorder = true // Add border
                        )
                        // Security Button
                        SettingsButton(
                            text = "Security",
                            icon = Icons.Default.Lock,
                            onClick = { /* Handle security click */ },
                            showBorder = false // No border
                        )
                        // Notifications Button
                        SettingsButton(
                            text = "Notifications",
                            icon = Icons.Default.Notifications,
                            onClick = { /* Handle notifications click */ },
                            showBorder = true // Add border
                        )
                        // Country Button
                        SettingsButton(
                            text = "Country",
                            icon = Icons.Default.LocationOn,
                            onClick = { /* Handle country click */ },
                            showBorder = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp)) // Space between the two boxes

                // Second Box for Dark Mode and Log Out
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Take full width within horizontal padding
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Dark Mode Button with Switch
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .border(width = 0.dp, color = Color.Black),
                            shape = RectangleShape,
                            onClick = { /**/ }
                        ) {
                            var isDarkModeEnabled by remember { mutableStateOf(false) }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Dark Mode")
                                Switch(
                                    checked = isDarkModeEnabled,
                                    onCheckedChange = { newValue ->
                                        isDarkModeEnabled = newValue
                                        println("Dark Mode is now: $isDarkModeEnabled")
                                        // Implement your actual dark mode logic here
                                    },
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                        // Log Out Button
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .border(width = 0.dp, color = Color.Black),
                            onClick = { /* Handle logout */ },
                            shape = RectangleShape
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Log Out")
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "Log Out Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

// Helper Composable for consistent settings buttons (except Dark Mode)
@Composable
fun SettingsButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    showBorder: Boolean
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(if (showBorder) Modifier.border(width = 1.dp, color = Color.Black) else Modifier),
        onClick = onClick,
        shape = RectangleShape // Rectangular shape for all buttons
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left-aligned content (Icon + Text)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$text Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text)
            }

            // Right-aligned arrow icon
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go to $text",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 700)
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}
