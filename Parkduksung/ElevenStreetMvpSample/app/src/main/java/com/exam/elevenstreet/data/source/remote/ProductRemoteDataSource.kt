package com.exam.elevenstreet.data.source.remote

import com.example.elevenstreet.ProductResponse

interface ProductRemoteDataSource {
    fun getProductRemoteData(
        keyword: String,
        productList: List<ProductResponse>
    )
}