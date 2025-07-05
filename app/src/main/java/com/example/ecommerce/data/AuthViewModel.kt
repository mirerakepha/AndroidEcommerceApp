package com.example.ecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ecommerce.models.User
import com.example.ecommerce.navigation.HOME_URL
import com.example.ecommerce.navigation.LOGIN_URL
import com.example.ecommerce.navigation.SIGNUP_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthViewModel(
    private val navController: NavController,
    private val context: Context
) : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val database: DatabaseReference = Firebase.database.reference

    fun signup(name: String, email: String, password: String, confPassword: String) {
        when {
            email.isBlank() || password.isBlank() || confPassword.isBlank() -> {
                showToast("Email and password cannot be blank")
            }
            password != confPassword -> {
                showToast("Passwords do not match")
            }
            else -> {
                val progressDialog = ProgressDialog(context).apply {
                    setMessage("Creating account...")
                    setCancelable(false)
                    show()
                }

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            val user = User(
                                name = name,
                                email = email,
                                password = password,
                                id = auth.currentUser?.uid ?: ""
                            )

                            database.child("Users").child(auth.currentUser?.uid ?: "")
                                .setValue(user)
                                .addOnCompleteListener { dbTask ->
                                    progressDialog.dismiss()
                                    if (dbTask.isSuccessful) {
                                        showToast("Registered successfully")
                                        navController.navigate(HOME_URL)
                                    } else {
                                        showToast("Database error: ${dbTask.exception?.message}")
                                        auth.currentUser?.delete() // Rollback auth if DB fails
                                        navController.navigate(SIGNUP_URL)
                                    }
                                }
                        } else {
                            progressDialog.dismiss()
                            showToast("Registration failed: ${authTask.exception?.message}")
                            navController.navigate(SIGNUP_URL)
                        }
                    }
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            showToast("Email and password cannot be blank")
            return
        }

        val progressDialog = ProgressDialog(context).apply {
            setMessage("Logging in...")
            setCancelable(false)
            show()
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    showToast("Login successful")
                    navController.navigate(HOME_URL)
                } else {
                    showToast("Login failed: ${task.exception?.message}")
                }
            }
    }

    fun logout() {
        auth.signOut()
        navController.navigate(LOGIN_URL) // Should navigate to login, not home
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}