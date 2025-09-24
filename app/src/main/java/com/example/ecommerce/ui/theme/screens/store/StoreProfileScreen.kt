package com.example.ecommerce.ui.theme.screens.store

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerce.models.Store
import com.example.ecommerce.ui.theme.Orange3
import com.example.ecommerce.R // Default drawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreProfileScreen(
    navController: NavController,
    store: Store,
    onSave: (Store) -> Unit = {}
) {
    var bannerUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf(store.name) }
    var location by remember { mutableStateOf(store.location) }
    var address by remember { mutableStateOf(store.address) }
    var zipcode by remember { mutableStateOf(store.zipcode) }
    var contact by remember { mutableStateOf(store.contactInfo) }
    var description by remember { mutableStateOf(store.description) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            bannerUri = uri
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Store Profile", color = Orange3) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Orange3)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val updatedStore = store.copy(
                            name = name,
                            location = location,
                            address = address,
                            zipcode = zipcode,
                            contactInfo = contact,
                            description = description,
                            bannerUrl = bannerUri?.toString() ?: store.bannerUrl
                        )
                        onSave(updatedStore)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Save", tint = Orange3)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                val painter = when {
                    bannerUri != null -> rememberAsyncImagePainter(bannerUri)
                    store.bannerUrl.isNotEmpty() -> rememberAsyncImagePainter(store.bannerUrl)
                    else -> painterResource(id = R.drawable.shopping)
                }

                Image(
                    painter = painter,
                    contentDescription = "Store Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            EditableField(label = "Store Name", value = name, onValueChange = { name = it })
            EditableField(label = "Location", value = location, onValueChange = { location = it })
            EditableField(label = "Address", value = address, onValueChange = { address = it })
            EditableField(label = "Zip Code", value = zipcode, onValueChange = { zipcode = it })
            EditableField(label = "Contact Info", value = contact, onValueChange = { contact = it })

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                maxLines = 5
            )
        }
    }
}

@Composable
fun EditableField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun StoreProfileScreenPreview() {
    val sampleStore = Store(
        id = "store1",
        name = "Finest Fibres",
        bannerUrl = "",
        location = "Nairobi",
        address = "123 Market Street",
        contactInfo = "+254 712 345678",
        zipcode = "00100",
        description = "We specialize in premium quality fabrics and fibers."
    )

    StoreProfileScreen(
        navController = rememberNavController(),
        store = sampleStore
    )
}
