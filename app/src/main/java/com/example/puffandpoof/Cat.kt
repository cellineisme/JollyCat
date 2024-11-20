package com.example.puffandpoof


val catList = arrayListOf(
    Cat("Persian", "With their snub noses, chubby cheeks, and long hair, the Persian cat is quite an exquisite breed. They make a perfect, purring lap warmer!", "20", R.drawable.persian,0 ),
    Cat("Sphynx", "Russian blues are medium-sized cats with plush, dense short coats of hair that stand out from their bodies and make them appear larger than they truly are.","25", R.drawable.sphynx,0),
    Cat("Russian Blue", "A Sphynx Cat is a hairless breed with a playful personality!","30",R.drawable.russianblue,0)
)


data class Cat (
    val catName: String,
    val catDesc: String,
    val catPrice: String,
    val catImg: Int,
    var purchaseQuantity: Int
        ) {

}

val arraylist = ArrayList<Cat>()