package com.exam.elevenstreet.network.model

data class ProductResponse @JvmOverloads constructor(
    var productCode: String = "",
    var productName: String = "",
    var productPrice: String = "",
    var productImage: String = ""
)