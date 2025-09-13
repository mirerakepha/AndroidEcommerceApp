package com.example.ecommerce.models

data class Store(
    val id: String = "",              // Unique ID (UID or storeId)
    val name: String = "",            // Store name
    val ownerName: String = "",       // Owner’s name
    val contact: String = "",         // Phone/email
    val logoUrl: String? = null,      // Store logo image in Firebase Storage
    val createdAt: Long = System.currentTimeMillis()
)
