package com.exam.elevenstreet.data

import com.exam.elevenstreet.network.model.ProductResponse

interface ProductCallback {
    fun onSuccess(productList: List<ProductResponse>)
    fun onFailure(message: String)
}