package com.example.ecommerce.ui.theme.screens.account

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.data.AuthViewModel
import com.example.ecommerce.data.ProfileViewModel
import com.example.ecommerce.navigation.HOME_URL
import com.example.ecommerce.navigation.STOREREGISTRATION_URL
import com.example.ecommerce.ui.theme.Orange3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreenUI(
    name: String,
    email: String,
    bio: String,
    profileImageUrl: Any?,
    isLoading: Boolean,
    errorMessage: String?,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSave: () -> Unit,
    onChangeImage: () -> Unit,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onStartStoreClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Account") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.Red)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile picture
            Box(contentAlignment = Alignment.BottomEnd) {
                val painter = when (profileImageUrl) {
                    is Int -> painterResource(profileImageUrl)
                    is String -> rememberAsyncImagePainter(profileImageUrl)
                    is Uri -> rememberAsyncImagePainter(profileImageUrl)
                    else -> painterResource(R.drawable.emn)
                }

                Image(
                    painter = painter,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onChangeImage,
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Change Profile", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = onBioChange,
                label = { Text("Bio") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Orange3, contentColor = Color.White)
            ) {
                Text("Save Profile")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onStartStoreClick,
                modifier = Modifier.width(250.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White)
            ) {
                Text("Start a store")
            }

            if (isLoading) CircularProgressIndicator()
            if (errorMessage != null) Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun AccountScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val name by profileViewModel.name.collectAsState()
    val email by profileViewModel.email.collectAsState()
    val bio by profileViewModel.bio.collectAsState()
    val profileImageUrl by profileViewModel.profileImageUrl.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> uri?.let { profileViewModel.updateProfileImage(it) } }
    )

    AccountScreenUI(
        name = name,
        email = email,
        bio = bio,
        profileImageUrl = profileImageUrl,
        isLoading = profileViewModel.isLoading,
        errorMessage = profileViewModel.errorMessage,
        onNameChange = { profileViewModel.updateName(it) },
        onBioChange = { profileViewModel.updateBio(it) },
        onSave = { profileViewModel.saveProfile() },
        onChangeImage = { imagePicker.launch("image/*") },
        onBackClick = { navController.popBackStack() },
        onLogoutClick = {
            authViewModel.logout()
            navController.navigate(HOME_URL) {
                popUpTo(0) { inclusive = true }
            }
        },
        onStartStoreClick = {
            navController.navigate(STOREREGISTRATION_URL)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    val navController = rememberNavController()
    AccountScreenUI(
        name = "BatMan",
        email = "batman@gmail.com",
        bio = "Here to save Gotham \uD83E\uDD87",
        profileImageUrl = R.drawable.emn,
        isLoading = false,
        errorMessage = null,
        onNameChange = {},
        onBioChange = {},
        onSave = {},
        onChangeImage = {},
        onBackClick = {},
        onLogoutClick = {},
        onStartStoreClick = {}
    )
}
