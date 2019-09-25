package com.exam.elevenstreet.data.source.local

import com.example.elevenstreet.ProductResponse

interface ProductLocalDataSource {

    fun getProductLocalData(
        productList: List<ProductResponse>
    )
}