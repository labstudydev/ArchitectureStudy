package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.view.product.App

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {

    override fun getProductList(keyWord: String, callback: ProductCallback) {
        if (App.instance.context().isConnectedToNetwork()) {
            productRemoteDataSource.getSearchByKeyword(keyWord, callback)
        } else {
            productLocalDataSource.getProductDataList(callback)
        }
    }

    companion object {

        private var instance: ProductRepositoryImpl? = null

        fun getInstance(
            remoteDataSource: ProductRemoteDataSource,
            localDataSource: ProductLocalDataSource
        ): ProductRepositoryImpl =
            instance ?: ProductRepositoryImpl(
                remoteDataSource,
                localDataSource).also {
                instance = it
            }
    }
}