package com.example.ecommerce.models

data class Product(
    val id: String = "",              // Unique product ID
    val storeId: String = "",         // Link product to a store
    val name: String = "",            // Product name
    val description: String = "",     // Product description
    val price: Double = 0.0,          // Price
    val imageUrl: String? = null,     // Product image in Firebase Storage
    val createdAt: Long = System.currentTimeMillis()
)

