package com.example.ecommerce.models

data class Rating(
    val userId: String,
    val storeId: String,
    val value: Float, // 1.0 - 5.0
    val comment: String = ""
)
