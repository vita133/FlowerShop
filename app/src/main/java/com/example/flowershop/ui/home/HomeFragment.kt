package com.example.flowershop.ui.home

import android.content.ContentValues
import com.example.flowershop.Adapters.ShopListAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowershop.ContentActivity
import com.example.flowershop.DataSource
import com.example.flowershop.Product
import com.example.flowershop.TopSpacingItemDecoration
import com.example.flowershop.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment(), ShopListAdapter.Listener {

    private var _binding: FragmentHomeBinding? = null
    private  var database: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val binding get() = _binding!!

    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()
        addDataSet()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        shopListAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            shopListAdapter = ShopListAdapter(this@HomeFragment)
            adapter = shopListAdapter
        }

    }

    override fun onClick(product: Product) {
        startActivity(Intent(activity, ContentActivity::class.java).apply{
            putExtra("item", product)
        })
    }

    override fun addToFavorite(product: Product){
        val id = product.id.toString()
        val name = product.name
        val price = product.price
        val image = product.image

        val infoProduct = HashMap<String,Any>()
        infoProduct.put("name", name!!)
        infoProduct.put("price", price!!)
        infoProduct.put("image", image!!)
        database.collection("favorite").document(id).set(infoProduct).addOnSuccessListener{
            Toast.makeText(activity, "Product to favorite saved", Toast.LENGTH_SHORT).show();
        }
            .addOnFailureListener {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
                Log.d(ContentValues.TAG, it.toString());
            }
    }

}