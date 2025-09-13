package com.example.ecommerce.data

import androidx.lifecycle.ViewModel
import com.example.ecommerce.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val database: DatabaseReference = Firebase.database.reference

    // Tracks whether a user is logged in
    private val _authState = MutableStateFlow(auth.currentUser != null)
    val authState = _authState.asStateFlow()

    // Tracks loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Tracks error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()


    fun signup(
        name: String,
        email: String,
        password: String,
        confPassword: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        if (email.isBlank() || password.isBlank() || confPassword.isBlank()) {
            onResult(false, "Email and password cannot be blank")
            return
        }
        if (password != confPassword) {
            onResult(false, "Passwords do not match")
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val user = User(name, email, userId)

                    database.child("Users").child(userId)
                        .setValue(user)
                        .addOnCompleteListener { dbTask ->
                            _isLoading.value = false
                            if (dbTask.isSuccessful) {
                                _authState.value = true
                                onResult(true, null)
                            } else {
                                // Rollback Firebase Auth user if DB write fails
                                auth.currentUser?.delete()?.addOnCompleteListener {
                                    onResult(false, dbTask.exception?.message ?: "Failed to save user data")
                                }
                            }
                        }
                } else {
                    _isLoading.value = false
                    onResult(false, authTask.exception?.message ?: "Signup failed")
                }
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                onResult(false, exception.message ?: "Signup failed")
            }
    }

    fun storeRegistration(
        name: String,
        email: String,
        password: String,
        confPassword: String,
        onResult: (Boolean, String?) -> Unit
    ) = signup(name, email, password, confPassword, onResult)


    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Email and password cannot be blank")
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    _authState.value = true
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message ?: "Login failed")
                }
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                onResult(false, exception.message ?: "Login failed")
            }
    }

    fun storeLogin(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) = login(email, password, onResult)


    fun logout() {
        auth.signOut()
        _authState.value = false
        _errorMessage.value = null
    }


    fun isLoggedIn(): Boolean = auth.currentUser != null
    fun getCurrentUserId(): String? = auth.currentUser?.uid
    fun clearError() { _errorMessage.value = null }
}
