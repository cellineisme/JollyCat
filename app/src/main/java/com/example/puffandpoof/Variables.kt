package com.example.puffandpoof

object Variables {
    private var IdCount = 1
    var usernamePass = ""
    var phonenumPass = ""
    var passwordPass = ""

    data class User(
        val username: String,
        val password: String,
        val phonenum: String,
        val transactions: ArrayList<Cart> = arrayListOf(), // Setiap pengguna memiliki daftar belanja mereka sendiri
        val id: String = randomizeID()
    )
    val arraylist = ArrayList<User>()
    var cartList : ArrayList<Cart> = arrayListOf()
    //var coList = ArrayList<>()

    private fun randomizeID(): String{
        val count = String.format("%04d", IdCount++)
        return "CId-$count"
    }

    fun transactionPass(): List<Cart>{
        return cartList
    }

    fun transactionAdd(cart: Cart){
        cartList.add(cart)
    }

    fun resetCart() {
        cartList.clear()
    }

}