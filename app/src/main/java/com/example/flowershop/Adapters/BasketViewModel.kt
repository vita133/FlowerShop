package com.example.flowershop.Adapters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowershop.Product
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class BasketViewModel : ViewModel(){

    val TAG = "FIRESTORE_VIEW_MODEL"
    var firebaseRepository = BasketRepository()
    var addedProducts : MutableLiveData<List<Product>> = MutableLiveData()


    init{
        getAddedProduct()
    }

    // save address to firebase
    fun addProductToFirebase(product: Product){
        firebaseRepository.addProduct(product).addOnFailureListener {
            Log.e(TAG,"Failed to add Product!")
        }


    }

    // get realtime updates from firebase regarding saved addresses
    fun getAddedProduct(): MutableLiveData<List<Product>> {
        firebaseRepository.getAddedProduct().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                //addedProducts.value = null
                return@EventListener
            }

            var addedProductsList : MutableList<Product> = addedProducts.value?.toMutableList() ?: mutableListOf()
            for (doc in value!!) {
                var productItem = doc.toObject(Product::class.java)
                addedProductsList.add(productItem)
            }
            addedProducts.value = addedProductsList
        })

        return addedProducts
    }

    // delete an address from firebase
    fun deleteProduct(product: Product){
        firebaseRepository.deleteProduct(product).addOnFailureListener {
            Log.e(TAG,"Failed to delete Address")
        }

    }

}