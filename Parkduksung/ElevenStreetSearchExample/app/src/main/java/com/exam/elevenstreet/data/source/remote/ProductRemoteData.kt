package com.exam.elevenstreet.data.source.remote

import com.example.elevenstreet.ProductResponse

interface ProductRemoteData {
    fun getProductRemoteData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    )
}