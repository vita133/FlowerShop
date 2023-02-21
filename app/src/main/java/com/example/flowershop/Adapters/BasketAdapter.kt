package com.example.flowershop.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowershop.Product
import com.example.flowershop.R
import com.example.flowershop.ui.notifications.NotificationsFragment

class BasketAdapter(val listener: NotificationsFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var products: List<Product> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasketHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.basket_row, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BasketHolder -> {
                holder.bind(products.get(position), listener)
            }
        }
    }

    override fun getItemCount() = products.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun submitList(productList: List<Product>) {
        products = productList
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class BasketHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, listener: NotificationsFragment) = with(itemView) {
            val nameBasketText = itemView.findViewById<TextView>(R.id.nameBasketText)
            val priceOneBasketTextView = itemView.findViewById<TextView>(R.id.priceOneBasket)
            val priceSumTextView = itemView.findViewById<TextView>(R.id.priceManyBasket)
            val quantityTextView = itemView.findViewById<TextView>(R.id.countText)

                nameBasketText.text = product.name
                priceOneBasketTextView.text = product.price.toString()
                priceSumTextView.text = product.price.toString()
                quantityTextView.text = "0"
        }
    }
}