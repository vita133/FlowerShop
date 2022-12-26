package com.example.flowershop

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flowershop.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
   private  var database: FirebaseFirestore = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)!!.findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        addDataToFirestore()
    }

    private fun setData(product: Product){
        val id = product.id.toString()
        val name = product.name
        val price = product.price.toString()
        val image = product.image

        val infoProduct = HashMap<String,Any>()
        infoProduct.put("name", name!!)
        infoProduct.put("price", price)
        infoProduct.put("image", image!!)
        database.collection("flowers").document(id).set(infoProduct)
    }

    private fun addDataToFirestore() {
        val productList = DataSource.createDataSet()
        for (element in productList) {
            setData(element)
        }
    }


}