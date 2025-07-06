package com.example.ecommerce.ui.theme.screens.account

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.data.ProfileViewModel

@Composable
fun AccountScreen(navController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    val context = LocalContext.current
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val bio by viewModel.bio.collectAsState()
    val profileImageUrl by viewModel.profileImageUrl.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.uploadProfileImage(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture Section
        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = profileImageUrl ?: R.drawable.itachih,
                    error = painterResource(R.drawable.itachih)
                ),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { imagePicker.launch("image/*") },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Change profile picture",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Form
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.updateName(it) },
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
            onValueChange = { viewModel.updateBio(it) },
            label = { Text("Bio") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Save Button
        Button(
            onClick = { viewModel.saveProfile() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading
        ) {
            Text("Save Profile")
        }

        // Loading/Error States
        when {
            viewModel.isLoading -> CircularProgressIndicator()
            viewModel.errorMessage != null -> Text(
                text = viewModel.errorMessage!!,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(navController = rememberNavController())
}