package com.example.flowershop.Adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowershop.Product
import com.example.flowershop.R
import com.bumptech.glide.request.RequestOptions
import com.example.flowershop.ui.home.HomeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShopListAdapter(val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
     var items: List<Product> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shop_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShopListViewHolder ->{
                holder.bind(items.get(position), listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(shopList: List<Product>){
        items = shopList
    }
    interface Listener{
        fun onClick(product: Product)
        fun addToFavorite(product: Product)
    }

    class ShopListViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val productImage = itemView.findViewById<ImageView>(R.id.productImageView)
        private val productName = itemView.findViewById<TextView>(R.id.productNameTextView)
        private val productPrice = itemView.findViewById<TextView>(R.id.priceTextView)
        private val favoriteButton = itemView.findViewById<FloatingActionButton>(R.id.favoriteButton)

        fun bind(product: Product, listener: Listener){
            productName.setText(product.name)
            productPrice.setText(product.price.toString())

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.image).into(productImage)

            itemView.setOnClickListener{
                listener.onClick(product)
            }

            favoriteButton.setOnClickListener{
                Log.d(ContentValues.TAG, "addToFavorite: added")
                listener.addToFavorite(product)
            }

        }
    }
}