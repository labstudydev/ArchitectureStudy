package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.App
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.network.model.ProductResponse

class ProductRepositoryImpl private constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {


    override fun getSearchByKeyword(
        keyWord: String,
        pageNum: Int,
        callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit
    ) {

        if (App.instance.context().isConnectedToNetwork()) {
            remoteDataSource.getProductList(keyWord, pageNum, callback)
        } else {
            localDataSource.getSampleProductList(callback)
        }
    }

    companion object {
        const val EXCEPTION = -1

        private var instance: ProductRepositoryImpl? = null
        // lazy initialize
        fun getInstance(
            remoteDataSource: ProductRemoteDataSource,
            localDataSource: ProductLocalDataSource
        ): ProductRepositoryImpl =
            instance ?: ProductRepositoryImpl(
                remoteDataSource,
                localDataSource
            ).also {
                instance = it
            }
    }
}