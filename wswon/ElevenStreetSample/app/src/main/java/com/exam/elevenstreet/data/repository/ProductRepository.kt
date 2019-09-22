package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.network.model.ProductResponse

interface ProductRepository {
    fun getSearchByKeyword(
        keyWord: String,
        pageNum: Int,
        callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit
    )
}