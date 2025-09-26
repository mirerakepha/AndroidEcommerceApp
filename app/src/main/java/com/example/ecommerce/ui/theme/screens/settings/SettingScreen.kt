package com.example.ecommerce.ui.theme.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.ecommerce.data.AuthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.ui.theme.ThemeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController,
                  themeState: ThemeState,
                  authViewModel: AuthViewModel? = null) {

    var isDarkModeEnabled by remember { mutableStateOf(themeState.isDarkTheme) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", color = MaterialTheme.colorScheme.onBackground) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Top Box with Profile/Settings buttons
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Orange3)
                        .border(1.dp, Orange3, RoundedCornerShape(15.dp))
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        SettingsButton(
                            text = "Profiles",
                            icon = Icons.Default.Person,
                            onClick = { /* TODO */ },
                            showBorder = true
                        )
                        SettingsButton(
                            text = "Security",
                            icon = Icons.Default.Lock,
                            onClick = { /* TODO */ },
                            showBorder = false
                        )
                        SettingsButton(
                            text = "Notifications",
                            icon = Icons.Default.Notifications,
                            onClick = { /* TODO */ },
                            showBorder = true
                        )
                        SettingsButton(
                            text = "Country",
                            icon = Icons.Default.LocationOn,
                            onClick = { /* TODO */ },
                            showBorder = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Bottom Box with Dark Mode toggle and Log Out
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        // Dark Mode toggle
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange3,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            onClick = { themeState.toggleTheme() }
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Dark Mode",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Switch(
                                    checked = themeState.isDarkTheme,
                                    onCheckedChange = { themeState.toggleTheme() },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Orange3,
                                        checkedTrackColor = Orange3.copy(alpha = 0.5f),
                                        uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                                        uncheckedTrackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                                    )
                                )
                            }
                        }


                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange3,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            onClick = {
                                authViewModel?.logout()
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Log Out",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "Log Out Icon"
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

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
            .then(if (showBorder) Modifier.border(1.dp, MaterialTheme.colorScheme.onPrimary) else Modifier),
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange3,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = "$text Icon", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text)
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Go to $text")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 700)
@Composable
fun SettingScreenPreview() {
    SettingScreen(
        navController = rememberNavController(),
        authViewModel = null,
        themeState = com.example.ecommerce.ui.theme.rememberThemeState())
}
