package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.data.ProductCallBack


interface ProductRemoteDataSource {
    fun getSearchByKeyword(
        keyword: String,
        callback: ProductCallBack
    )
}