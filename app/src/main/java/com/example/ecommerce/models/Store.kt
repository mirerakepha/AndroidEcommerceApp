package com.example.ecommerce.models

data class Store(
    val id: String = "",              //  storeId
    val name: String = "",            // Store name
    val ownerName: String = "",       // Owner’s name
    val contact: String = "",         // Phone/email
    val logoUrl: String? = null,      // Store logo image
    val createdAt: Long = System.currentTimeMillis()
)
