package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.data.ProductCallback

interface ProductRemoteDataSource {
    fun getSearchByKeyword(keyWord: String, callback: ProductCallback)
}