package com.example.ecommerce.models

data class Store(
    val id: String = "",
    val name: String = "",
    val bannerUrl: String = "",
    val location: String = "",
    val address: String = "",
    val contactInfo: String = "",
    val zipcode: String = "",
    val description: String = "",
    val logoUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
