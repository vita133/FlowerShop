package com.example.flowershop.ui.dashboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowershop.Adapters.FavoriteAdapterFireStore
import com.example.flowershop.Product
import com.example.flowershop.databinding.FragmentDashboardBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.*


class DashboardFragment : Fragment(), FavoriteAdapterFireStore.onButtonClicked {
    private var _binding: FragmentDashboardBinding? = null


    private val db = FirebaseFirestore.getInstance()
    private val favoriteItemsRef = db.collection("favorite")

    private var adapter: FavoriteAdapterFireStore? = null
    private val binding get() = _binding!!


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

        setUpRecyclerView()
        adapter?.startListening();

        favoriteItemsRef.addSnapshotListener() { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                setUpRecyclerView()
                adapter?.startListening()
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter?.stopListening();
    }

private fun setUpRecyclerView() {
    val query: Query = favoriteItemsRef
    val options: FirestoreRecyclerOptions<Product> = FirestoreRecyclerOptions.Builder<Product>()
        .setQuery(query, Product::class.java)
        .build()
    adapter = FavoriteAdapterFireStore(options, this)
    val recyclerView: RecyclerView = binding.favoriteRecyclerView
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(activity)
    recyclerView.adapter = adapter
}

    override fun onButton(position: Int) {
        Toast.makeText(activity, "Deleted$position", Toast.LENGTH_SHORT).show()
    }

}


