package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.Callback
import com.example.elevenstreet.ProductResponse

interface ProductLocalDataSourceInterface {
    fun getProductList(callback: (productList: List<ProductResponse>) -> Unit)
}