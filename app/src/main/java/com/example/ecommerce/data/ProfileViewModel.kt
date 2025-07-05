package com.example.ecommerce.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _bio = MutableStateFlow("")
    val bio: StateFlow<String> = _bio

    private val _profileImageUri = MutableStateFlow<Uri?>(null)
    val profileImageUri: StateFlow<Uri?> = _profileImageUri

    fun updateName(value: String) { _name.value = value }
    fun updateEmail(value: String) { _email.value = value }
    fun updateBio(value: String) { _bio.value = value }

    fun updateProfileImage(uri: Uri?) {
        _profileImageUri.value = uri
    }

    fun saveProfile(context: Context) {
        // You could save to DataStore, Room, or SharedPreferences here
        Toast.makeText(context, "Profile Saved!", Toast.LENGTH_SHORT).show()
    }
}