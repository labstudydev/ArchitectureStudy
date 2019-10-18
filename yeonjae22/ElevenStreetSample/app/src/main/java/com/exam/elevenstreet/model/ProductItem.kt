package com.exam.elevenstreet.model

import android.graphics.Bitmap
import java.io.Serializable

data class ProductItem @JvmOverloads constructor(
    var productName: String = "",
    var productPrice: String = "",
    var productImage: Bitmap? = null,
    var productSeller: String = "",
    var productDelivery: String = ""
) : Serializable