package com.exam.elevenstreet.data.repository

import com.example.elevenstreet.ProductResponse

interface ProductRepository {
    fun getProductRepositoryRemoteData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    )

    fun getProductRepositoryLocalData(
        callback: (productList: List<ProductResponse>) -> Unit
    )
}