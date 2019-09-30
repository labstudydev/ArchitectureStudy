package com.exam.elevenstreet.data.model

data class ProductItem @JvmOverloads constructor(
    var productCode: String = "",
    var productName: String = "",
    var productPrice: String = "",
    var productImage: String = ""
)