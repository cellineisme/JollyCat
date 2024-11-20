package com.example.puffandpoof

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.Variables
import com.example.puffandpoof.R
import java.util.*

class CartAdapter(var cartList: List<Cart>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

     class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.cart_item_name_text_view)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.cart_item_price_text_view)
        val quantityTextView: TextView = itemView.findViewById(R.id.cart_item_quantity_text_view)
        val subtotalTextView: TextView = itemView.findViewById(R.id.cart_item_subtotal_text_view)
         val imageView: ImageView = itemView.findViewById(R.id.cart_item_image_view)
         val updateButton: Button = itemView.findViewById(R.id.updateButton)
         val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cartpage, parent, false)
        return CartViewHolder(itemView)
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartList[position]

        holder.itemNameTextView.text = currentItem.catName
        holder.itemPriceTextView.text = currentItem.catPrice
        holder.quantityTextView.text = currentItem.purchaseQuantity.toString() // Display quantity
        holder.subtotalTextView.text = currentItem.subtotalPrice
        holder.imageView.setImageResource(currentItem.catImageResId)


        holder.updateButton.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialog_update_quantity, null)
            val editText = dialogView.findViewById<EditText>(R.id.editTextQuantity)

            builder.setTitle("Update Quantity")
            builder.setView(dialogView)
            builder.setPositiveButton("Update") { dialog, which ->
                val quantityString = editText.text.toString().trim()
                if (quantityString.isNotEmpty() && quantityString.matches("\\d+".toRegex())) {
                    val newQuantity = quantityString.toInt()
                    if (newQuantity > 0) {
                        // Update quantity and subtotal
                        currentItem.purchaseQuantity = newQuantity.toString()
                        currentItem.updateSubtotalPrice()
                        notifyItemChanged(holder.adapterPosition)
                        // TODO: Update the transaction data in the application's database
                    } else {
                        Toast.makeText(context, "Quantity must be more than 0", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Invalid quantity", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }

        // Delete button click listener
        holder.deleteButton.setOnClickListener {
            val context = holder.itemView.context
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val mutableCartList = cartList.toMutableList()
                mutableCartList.removeAt(position)
                cartList = mutableCartList.toList()
                notifyItemRemoved(position)
                Toast.makeText(context, "Transaction deleted", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun getItemCount() : Int{
        return cartList.size
    }
}

