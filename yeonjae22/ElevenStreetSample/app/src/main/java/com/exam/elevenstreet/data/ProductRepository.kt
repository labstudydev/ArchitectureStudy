package com.exam.elevenstreet.data

import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App
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
        if (App.instance.context().isConnectedToNetwork()) {
            productRemoteDataSource.getSearchByKeyword(keyWord, callback)
        } else {
            productLocalDataSource.loadCacheProductData(callback)
        }
    }

    companion object {
        private var instance: ProductRepository? = null
        fun getInstance(
            productRemoteDataSource: ProductRemoteDataSource,
            productLocalDataSource: ProductLocalDataSource
        ): ProductRepository =
            instance ?: ProductRepository(
                productRemoteDataSource,
                productLocalDataSource
            ).also {
                instance = it
            }
    }
}