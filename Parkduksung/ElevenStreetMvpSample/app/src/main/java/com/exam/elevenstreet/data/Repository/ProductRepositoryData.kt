package com.exam.elevenstreet.data.Repository

import com.example.elevenstreet.ProductResponse

interface ProductRepositoryData {
    fun getProductRepositoryRemoteData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    )

    fun getProductRepositoryLocalData(
        callback: (productList: List<ProductResponse>) -> Unit
    )
}