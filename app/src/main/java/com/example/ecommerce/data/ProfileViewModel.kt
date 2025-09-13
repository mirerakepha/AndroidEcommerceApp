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
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
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
    private val db = Firebase.firestore

    private val userId: String?
        get() = auth.currentUser?.uid

    init {
        loadUserData()
        loadProfileImage()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            isLoading = true
            try {
                auth.currentUser?.let { user ->
                    _name.value = user.displayName ?: ""
                    _email.value = user.email ?: ""
                }

                userId?.let { uid ->
                    val doc = db.collection("users").document(uid).get().await()
                    if (doc.exists()) {
                        _bio.value = doc.getString("bio") ?: ""
                        _profileImageUrl.value = doc.getString("profileImageUrl")
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Failed to load profile: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    private fun loadProfileImage() {
        viewModelScope.launch {
            try {
                userId?.let { uid ->
                    val url = storage.reference
                        .child("profile_images/$uid.jpg")
                        .downloadUrl
                        .await()
                    _profileImageUrl.value = url.toString()
                }
            } catch (_: Exception) {
                // No existing image is fine
            }
        }
    }

    fun updateName(value: String) { _name.value = value }
    fun updateBio(value: String) { _bio.value = value }

    fun updateProfileImage(uri: Uri) {
        _profileImageUrl.value = uri // show immediately
        uploadProfileImage(uri)
    }

    private fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                userId?.let { uid ->
                    val ref = storage.reference.child("profile_images/$uid.jpg")
                    ref.putFile(uri).await()
                    val downloadUrl = ref.downloadUrl.await().toString()

                    _profileImageUrl.value = downloadUrl

                    // Save download URL to Firestore
                    db.collection("users").document(uid)
                        .set(mapOf("profileImageUrl" to downloadUrl), SetOptions.merge())
                        .await()
                }
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
            errorMessage = null
            try {
                userId?.let { uid ->
                    // Update Firebase Auth displayName
                    auth.currentUser?.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(_name.value)
                            .build()
                    )?.await()

                    // Save bio + name + imageUrl to Firestore
                    db.collection("users").document(uid)
                        .set(
                            mapOf(
                                "name" to _name.value,
                                "bio" to _bio.value,
                                "profileImageUrl" to _profileImageUrl.value
                            ),
                            SetOptions.merge()
                        )
                        .await()
                }
            } catch (e: Exception) {
                errorMessage = "Save failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
