package com.example.flowershop

import java.io.Serializable

class Product : Serializable {
    var id: Int? = null
    var name: String? = null
    var price: Int? = null
    var image: String? = null

    constructor(){}

    constructor( id: Int?, name: String?, price: Int?, imageUrl: String?) {
        this.id = id
        this.name = name
        this.price = price
        this.image = imageUrl
    }

}