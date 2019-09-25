package com.exam.elevenstreet.data.repository

import com.example.elevenstreet.ProductResponse

interface ProductRepository {
    fun getProductRepositoryRemoteData(
        keyword: String,
        productList: List<ProductResponse>
    )


    fun getProductRepositoryLocalData(
        productList: List<ProductResponse>
    )
}