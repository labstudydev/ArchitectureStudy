package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.ProductCallback

interface ProductRepository {
    fun getProductList(keyWord: String, callback: ProductCallback)
}