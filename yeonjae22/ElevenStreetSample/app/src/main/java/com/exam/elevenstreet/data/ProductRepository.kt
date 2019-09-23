package com.exam.elevenstreet.data

import com.example.elevenstreet.ProductResponse

class ProductRepository(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) {

    interface CallBack {
        fun onSuccess(productList: List<ProductResponse>)
        fun onFailure(message: String)
    }

    fun getSearchByKeyword(
        keyWord: String,
        callback: CallBack
    ) {
        productRemoteDataSource.getSearchByKeyword(keyWord, callback)
    }

    fun getProductList(
        callback: CallBack
    ) {
        productLocalDataSource.getProductList(callback)
    }

}