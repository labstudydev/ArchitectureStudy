package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App

class ProductRepositoryImpl private constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
): ProductRepository {

    override fun getSearchByKeyword(
        keyWord: String,
        callback: ProductCallBack
    ) {
        if (App.instance.context().isConnectedToNetwork()) {
            productRemoteDataSource.getSearchByKeyword(keyWord, callback)
        } else {
            productLocalDataSource.loadCacheProductData(callback)
        }
    }

    companion object {
        private var instance: ProductRepositoryImpl? = null
        fun getInstance(
            productRemoteDataSource: ProductRemoteDataSource,
            productLocalDataSource: ProductLocalDataSource
        ): ProductRepositoryImpl =
            instance
                ?: ProductRepositoryImpl(
                    productRemoteDataSource,
                    productLocalDataSource
                ).also {
                instance = it
            }
    }
}