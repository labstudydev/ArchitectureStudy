package com.example.elevenstreet

data class ProductResponse @JvmOverloads constructor(
    var productCode: String = "",
    var productName: String = "",
    var productPrice: String = "",
    var productImage: String = "",
    var productSeller: String = "",
    var productDelivery: String = ""
)