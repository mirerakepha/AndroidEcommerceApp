package com.example.ecommerce.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.ecommerce.models.CartItem

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(product: CartItem) {
        val existing = _cartItems.find { it.id == product.id }
        if (existing != null) {
            val index = _cartItems.indexOf(existing)
            _cartItems[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _cartItems.add(product)
        }
    }

    fun removeFromCart(product: CartItem) {
        _cartItems.remove(product)
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun increaseQuantity(product: CartItem) {
        val index = _cartItems.indexOf(product)
        if (index >= 0) {
            val updated = _cartItems[index].copy(quantity = _cartItems[index].quantity + 1)
            _cartItems[index] = updated
        }
    }

    fun decreaseQuantity(product: CartItem) {
        val index = _cartItems.indexOf(product)
        if (index >= 0 && _cartItems[index].quantity > 1) {
            val updated = _cartItems[index].copy(quantity = _cartItems[index].quantity - 1)
            _cartItems[index] = updated
        }
    }
}
