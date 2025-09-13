package com.example.ecommerce.data

import androidx.lifecycle.ViewModel
import com.example.ecommerce.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = Firebase.database.reference

    // Tracks whether a user is logged in
    private val _authState = MutableStateFlow(auth.currentUser != null)
    val authState = _authState.asStateFlow()

    // Tracks loading state (login/signup/etc.)
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Tracks errors
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    // --- Signup ---
    fun signup(name: String, email: String, password: String, confPassword: String, onResult: (Boolean, String?) -> Unit) {
        if (email.isBlank() || password.isBlank() || confPassword.isBlank()) {
            onResult(false, "Email and password cannot be blank")
            return
        }
        if (password != confPassword) {
            onResult(false, "Passwords do not match")
            return
        }

        _isLoading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val user = User(
                        name = name,
                        email = email,
                        password = password,
                        id = auth.currentUser?.uid ?: ""
                    )
                    database.child("Users").child(user.id)
                        .setValue(user)
                        .addOnCompleteListener { dbTask ->
                            _isLoading.value = false
                            if (dbTask.isSuccessful) {
                                _authState.value = true
                                onResult(true, null)
                            } else {
                                auth.currentUser?.delete() // rollback
                                onResult(false, dbTask.exception?.message)
                            }
                        }
                } else {
                    _isLoading.value = false
                    onResult(false, authTask.exception?.message)
                }
            }
    }

    // --- Store Signup ---
    fun storeSignup(name: String, email: String, password: String, confPassword: String, onResult: (Boolean, String?) -> Unit) {
        signup(name, email, password, confPassword, onResult) // can extend later if stores need more fields
    }

    // --- Login ---
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Email and password cannot be blank")
            return
        }

        _isLoading.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    _authState.value = true
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    // --- Store Login ---
    fun storeLogin(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        login(email, password, onResult) // can extend later for store-specific logic
    }

    // --- Logout ---
    fun logout() {
        auth.signOut()
        _authState.value = false
    }

    // --- Check if logged in ---
    fun isLoggedIn(): Boolean = auth.currentUser != null
}
