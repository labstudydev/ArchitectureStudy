package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.ProductCallBack

interface ProductRepository {
    fun getSearchByKeyword(
        keyWord: String,
        callback: ProductCallBack
    )
}