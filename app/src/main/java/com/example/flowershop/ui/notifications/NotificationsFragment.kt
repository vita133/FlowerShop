package com.example.flowershop.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowershop.Adapters.BasketAdapter
import com.example.flowershop.Adapters.BasketViewModel
import com.example.flowershop.Product
import com.example.flowershop.TopSpacingItemDecoration
import com.example.flowershop.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var basketRecyclerView: RecyclerView

    private lateinit var BasketAdapter: BasketAdapter
    lateinit var  basketViewModel : BasketViewModel


    //private val userViewModel by lazy {ViewModelProviders.of(this).get(BasketViewModel::class.java)}

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        basketViewModel = ViewModelProvider(this).get(BasketViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()
        addDataSet()

//        var data: List<Product>? = basketViewModel.getAddedProduct().value
//        basketViewModel.getAddedProduct().observe(viewLifecycleOwner, Observer  { it->
//            data = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addDataSet() {
        var data: List<Product>? = basketViewModel.getAddedProduct().value
        data?.let { BasketAdapter.submitList(it) }
 //       BasketAdapter.submitList(DataSource.createDataSet())
    }

    private fun initRecyclerView() {
        binding.basketRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            BasketAdapter = BasketAdapter(this@NotificationsFragment)
            adapter = BasketAdapter
        }
    }

}