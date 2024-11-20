package com.example.puffandpoof

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class CatAdapter(private val catList: List<Cat>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(item: Cat)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define views in the ViewHolder if needed
        val nameTextView = itemView.findViewById<TextView>(R.id.cat_item_name_text_view)
        val descTextView = itemView.findViewById<TextView>(R.id.cat_item_desc_text_view)
        val priceTextView = itemView.findViewById<TextView>(R.id.cat_item_price_text_view)
        val imageView = itemView.findViewById<ImageView>(R.id.cat_item_image_view)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout for the ViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = catList[position]

        holder.nameTextView.text = currentItem.catName
        holder.descTextView.text = currentItem.catDesc
        holder.priceTextView.text = currentItem.catPrice.toString()
        holder.imageView.setImageResource(currentItem.catImg)

        holder.cardView.setOnClickListener{
            itemClickListener.onItemClick(currentItem)
        }
    }


}
