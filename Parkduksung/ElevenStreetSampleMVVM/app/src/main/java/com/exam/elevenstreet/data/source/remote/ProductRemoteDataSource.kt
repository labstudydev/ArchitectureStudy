package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.network.model.ProductResponse

interface ProductRemoteDataSource {
    fun getProductList(
        keyWord: String,
        pageNum: Int,
        callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit
    )
}