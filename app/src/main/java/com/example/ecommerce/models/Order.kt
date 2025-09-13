package com.example.ecommerce.models

data class Order(
    val id: String = "",              // Order ID
    val productId: String = "",
    val buyerId: String = "",
    val storeId: String = "",
    val quantity: Int = 1,
    val totalPrice: Double = 0.0,
    val status: String = "pending",   // pending, confirmed, shipped
    val createdAt: Long = System.currentTimeMillis()
)
