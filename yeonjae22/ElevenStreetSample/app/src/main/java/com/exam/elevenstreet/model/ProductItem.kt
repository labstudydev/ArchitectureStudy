package com.exam.elevenstreet.model

import android.graphics.Bitmap

data class ProductItem @JvmOverloads constructor(
    var productName: String = "",
    var productPrice: String = "",
    var productImage: Bitmap? = null
)