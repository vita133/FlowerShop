package com.example.flowershop

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.flowershop.databinding.ActivityContentBinding
import com.google.firebase.firestore.FirebaseFirestore

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra("item") as Product
        binding.apply {
            Glide.with(this@ContentActivity).load(item.image).into(imageView)
            nameTextView.text = item.name
            priceContent.text = item.price.toString()
        }
        binding.favoriteButton2.setOnClickListener() {
            val id = item.id.toString()
            val name = item.name
            val price = item.price
            val image = item.image

            val infoProduct = HashMap<String, Any>()
            infoProduct.put("name", name!!)
            infoProduct.put("price", price!!)
            infoProduct.put("image", image!!)
            database
                .collection("favorite")
                .document(id)
                .set(infoProduct)
                .addOnSuccessListener {
                    Toast.makeText(this, "Product to favorite saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error add favorite content!", Toast.LENGTH_SHORT).show()
                    Log.d(ContentValues.TAG, it.toString())
                }
        }
    }
}
