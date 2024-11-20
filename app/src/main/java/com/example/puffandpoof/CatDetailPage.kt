package com.example.puffandpoof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.*

class CatDetailPage : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var backButton: Button
    private lateinit var buyButton: Button

    private var purchaseQuantity: Int = 0 // Variable to store quantity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail_page)

        nameTextView = findViewById(R.id.cat_detail_name_text)
        descTextView = findViewById(R.id.cat_detail_desc_text)
        priceTextView = findViewById(R.id.cat_detail_price_text)
        imageView = findViewById(R.id.cat_detail_image_view)
        backButton = findViewById(R.id.cat_detail_back_button)
        buyButton = findViewById(R.id.cat_detail_buy_button)

        val catName = intent.getStringExtra("catName")
        val catDesc = intent.getStringExtra("catDesc")
        val catPrice = intent.getStringExtra("catPrice")
        val catImage = intent.getIntExtra("catImg", R.drawable.persian)

        nameTextView.text = catName
        descTextView.text = catDesc
        priceTextView.text = catPrice
        imageView.setImageResource(catImage)

        backButton.setOnClickListener {
            finish()
        }

        buyButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder) {
                setTitle("Quantity:")
                setPositiveButton("OK") { dialog, which ->
                    val quantityString = editText.text.toString()
                    val namaKucing = catName ?: ""
                    val hargaKucing = catPrice ?: ""
                    val descKucing = catDesc ?: ""

                    if (quantityString.isNotEmpty() && quantityString.matches("\\d+".toRegex())) {
                        val quantity = quantityString.toIntOrNull()
                        val price = hargaKucing.toIntOrNull()
                        val subtotalPrice = quantity?.times(price!!)

                        val orderID = generateOrderID()
                        val currentUser = Variables.arraylist.find { it.username == Variables.usernamePass }
                        currentUser?.let { user ->
                            val catName = intent.getStringExtra("catName")
                            val catDesc = intent.getStringExtra("catDesc")
                            val catPrice = intent.getStringExtra("catPrice")
                            val catImage = intent.getIntExtra("catImg", R.drawable.persian)

                            val quantityString = editText.text.toString()
                            val quantity = quantityString.toIntOrNull() ?: 0
                            val price = catPrice?.toIntOrNull() ?: 0
                            val subtotalPrice = quantity * price
                            val cartItem = Cart(catName ?: "", catPrice ?: "", quantity.toString(), subtotalPrice.toString(), catImage)
                            user.transactions.add(cartItem) // Add transaction to the user's list
                            Toast.makeText(this@CatDetailPage, "Check your cart", Toast.LENGTH_SHORT).show()
                        }


                    } else {
                        // Invalid input
                        Toast.makeText(this@CatDetailPage, "Invalid quantity", Toast.LENGTH_SHORT).show()
                    }
                }
                setNegativeButton("CANCEL") { dialog, which ->
                    Log.d("Main", "Canceled")
                }
                setView(dialogLayout)
                show()
            }
        }

    }
    fun generateOrderID(): String {
        return UUID.randomUUID().toString()
    }

}
