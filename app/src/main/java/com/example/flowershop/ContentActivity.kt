package com.example.flowershop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.flowershop.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getSerializableExtra("item") as Product
        binding.apply {
            Glide.with(this@ContentActivity)
                .load(item.image)
                .into(imageView)
            nameTextView.text = item.name
            priceContent.text = item.price.toString()

        }
    }
}