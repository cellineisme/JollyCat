package com.example.puffandpoof

import java.util.*
import kotlin.collections.ArrayList

data class Cart(
    val catName: String,
    val catPrice: String,
    var purchaseQuantity: String,
    var subtotalPrice: String,
    val catImageResId: Int,
    var checkoutId: String = ""
) {
    fun updateSubtotalPrice() {
        subtotalPrice = (catPrice.toInt() * purchaseQuantity.toInt()).toString()
    }
    fun resetCart() {
        Variables.cartList.clear()
    }

}

val cartList = ArrayList<Cart>()