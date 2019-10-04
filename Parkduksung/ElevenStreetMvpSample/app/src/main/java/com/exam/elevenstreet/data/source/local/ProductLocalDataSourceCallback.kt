package com.exam.elevenstreet.data.source.local

import com.example.elevenstreet.ProductResponse

interface ProductLocalDataSourceCallback {
    fun getProductList(productList: List<ProductResponse>)

}