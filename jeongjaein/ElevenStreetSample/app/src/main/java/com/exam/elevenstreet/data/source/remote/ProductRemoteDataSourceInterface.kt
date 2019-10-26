package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.data.Callback
import com.example.elevenstreet.ProductResponse


interface ProductRemoteDataSourceInterface {
    fun getProductList(
        keyWord: String,
        pageNum: Int,
        callback: (productList: List<ProductResponse>) -> Unit
    )
}