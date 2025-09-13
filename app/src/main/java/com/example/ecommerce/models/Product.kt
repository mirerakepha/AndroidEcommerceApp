package com.example.ecommerce.data

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val category: String = "",
    val storeId: String = ""
) {

    constructor() : this("", "", "", 0.0, "", "", "")
}