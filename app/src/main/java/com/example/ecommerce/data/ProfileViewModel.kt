package com.example.ecommerce.data

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {
    // User Data
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _bio = MutableStateFlow("")
    val bio: StateFlow<String> = _bio.asStateFlow()

    // Profile Image
    private val _profileImageUrl = MutableStateFlow<Any?>(null)
    val profileImageUrl: StateFlow<Any?> = _profileImageUrl.asStateFlow()

    // UI State
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val auth: FirebaseAuth = Firebase.auth
    private val storage = Firebase.storage
    private val userId = auth.currentUser?.uid ?: ""

    init {
        loadUserData()
        loadProfileImage()
    }

    private fun loadUserData() {
        auth.currentUser?.let { user ->
            _name.value = user.displayName ?: ""
            _email.value = user.email ?: ""
        }
    }

    private fun loadProfileImage() {
        viewModelScope.launch {
            isLoading = true
            try {
                val url = storage.reference
                    .child("profile_images/$userId.jpg")
                    .downloadUrl
                    .await()
                _profileImageUrl.value = url.toString()
            } catch (e: Exception) {
                // No existing image for first-time users
            } finally {
                isLoading = false
            }
        }
    }

    fun updateName(value: String) { _name.value = value }
    fun updateBio(value: String) { _bio.value = value }

    /*
     * Called from UI when a new image is picked.
     * Shows it immediately, then uploads to Firebase Storage.
     */
    fun updateProfileImage(uri: Uri) {
        // Show the picked image immediately
        _profileImageUrl.value = uri
        // Upload to Firebase in background
        uploadProfileImage(uri)
    }

    private fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val ref = storage.reference
                    .child("profile_images/$userId.jpg")

                ref.putFile(uri).await()
                _profileImageUrl.value = ref.downloadUrl.await().toString()
            } catch (e: Exception) {
                errorMessage = "Upload failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun saveProfile() {
        viewModelScope.launch {
            isLoading = true
            try {
                auth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(_name.value)
                        .build()
                )?.await()
            } catch (e: Exception) {
                errorMessage = "Save failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
