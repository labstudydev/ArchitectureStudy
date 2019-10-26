package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.Callback
import com.example.elevenstreet.ProductResponse

interface ProductRepositoryInterface {
    fun getProductItem(
        keyWord: String,
        pageNum: Int,
        callback: (productList:List<ProductResponse>) -> Unit
    )
}