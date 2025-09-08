package com.example.ecommerce.models

data class CartItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 1
)
