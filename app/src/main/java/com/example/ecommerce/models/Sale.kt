package com.example.ecommerce.models

data class Sale(
    val id: String = "",
    val orderId: String = "",
    val storeId: String = "",
    val amount: Double = 0.0,
    val date: Long = System.currentTimeMillis()
)
