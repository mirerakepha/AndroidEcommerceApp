package com.example.ecommerce.ui.theme.screens.account

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.data.ProfileViewModel

@Composable
fun AccountScreenUI(
    name: String,
    email: String,
    bio: String,
    profileImageUrl: Any?,
    isLoading: Boolean,
    errorMessage: String?,
    onNameChange: (String) -> Unit = {},
    onBioChange: (String) -> Unit = {},
    onSave: () -> Unit = {},
    onChangeImage: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Pic
        Box(contentAlignment = Alignment.BottomEnd) {
            val painter = when (profileImageUrl) {
                is Int -> painterResource(profileImageUrl) // Drawable resource
                is String -> rememberAsyncImagePainter(profileImageUrl) // URL
                is Uri -> rememberAsyncImagePainter(profileImageUrl) // Gallery Uri
                else -> painterResource(R.drawable.emn) // Placeholder avatar
            }

            Image(
                painter = painter,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Upload Button (edit icon)
            IconButton(
                onClick = { onChangeImage?.invoke() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Change Profile Pic",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Form
        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
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
            onValueChange = { onBioChange(it) },
            label = { Text("Bio") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Save Button
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Save Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading/Error States
        when {
            isLoading -> CircularProgressIndicator()
            errorMessage != null -> Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun AccountScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val bio by viewModel.bio.collectAsState()
    val profileImageUrl by viewModel.profileImageUrl.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.uploadProfileImage(it) } // upload new image
    }

    AccountScreenUI(
        name = name,
        email = email,
        bio = bio,
        profileImageUrl = profileImageUrl,
        isLoading = viewModel.isLoading,
        errorMessage = viewModel.errorMessage,
        onNameChange = { viewModel.updateName(it) },
        onBioChange = { viewModel.updateBio(it) },
        onSave = { viewModel.saveProfile() },
        onChangeImage = { imagePicker.launch("image/*") } //opens gallery
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreenUI(
        name = "Falcon Webs",
        email = "kephamirera16@gmail.com",
        bio = "Some nerdy stuff 🗑️",
        profileImageUrl = R.drawable.emn, //avatar
        isLoading = false,
        errorMessage = null
    )
}
