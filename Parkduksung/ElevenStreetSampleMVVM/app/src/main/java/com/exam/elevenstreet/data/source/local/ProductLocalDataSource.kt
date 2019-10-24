package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.network.model.ProductResponse

interface ProductLocalDataSource {
    fun getSampleProductList(callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit)
}