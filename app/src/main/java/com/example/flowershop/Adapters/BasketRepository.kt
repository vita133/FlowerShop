package com.example.flowershop.Adapters

import com.example.flowershop.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class BasketRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    lateinit var product: Product


    // save address to firebase
    fun addProduct(product: Product): Task<Void> {
        //var
        val id = product.id.toString()
        val name = product.name
        val price = product.price
        val image = product.image
//        val priceSum = product.price
//        val quantity = 1

        val infoProduct = HashMap<String, Any>()
        infoProduct.put("name", name!!)
        infoProduct.put("price", price!!)
        infoProduct.put("image", image!!)
//        infoProduct.put("priceSum", priceSum!!)
//        infoProduct.put("quantity", quantity)

        var documentReference = firestoreDB.collection("basket").document(id).set(infoProduct)
        return documentReference
    }

    // get saved addresses from firebase
    fun getAddedProduct(): CollectionReference {
        return firestoreDB.collection("basket")
    }

    fun deleteProduct(product: Product): Task<Void> {
        var documentReference =  firestoreDB.collection("basket")
            .document(product.id.toString())

        return documentReference.delete()
    }

}