package com.example.flowershop.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowershop.TopSpacingItemDecoration
import com.example.flowershop.databinding.FragmentDashboardBinding
import FavoriteListAdapter
import android.util.Log
import com.example.flowershop.Product
import com.google.firebase.firestore.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private var database = FirebaseFirestore.getInstance()
    private var datafavotite = ArrayList<Product>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.text.observe(viewLifecycleOwner) {
        }

        initRecyclerView()
        EventChangeListener()
        addDataSet()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addDataSet() {
        favoriteListAdapter.submitList(datafavotite)
    }

    private fun initRecyclerView() {
        binding.favoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            favoriteListAdapter = FavoriteListAdapter(this@DashboardFragment)
            adapter = favoriteListAdapter
        }

    }

    fun EventChangeListener(){
        database.collection("favorite")
            .addSnapshotListener(object: EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null){
                        Log.e("Firestore error", error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            datafavotite.add(dc.document.toObject(Product::class.java))
                        }
                    }
                    favoriteListAdapter.notifyDataSetChanged()
                }
            })
    }

     fun deleteFromFavorite(product: Product) {

    }
    fun addToBasket(product: Product){
    }
}