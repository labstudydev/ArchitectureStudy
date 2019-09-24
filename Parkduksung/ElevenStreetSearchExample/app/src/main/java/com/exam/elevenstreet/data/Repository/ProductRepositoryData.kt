package com.exam.elevenstreet.data.Repository

import com.example.elevenstreet.ProductResponse

interface ProductRepositoryData {
    fun getProductRepositoryData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    )

    fun getProductRepositoryData2(
        callback: (productList: List<ProductResponse>) -> Unit
    )
}