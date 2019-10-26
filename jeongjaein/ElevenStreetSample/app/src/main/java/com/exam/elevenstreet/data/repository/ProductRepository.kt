package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.MyApplication
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceInterface
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceInterface
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.example.elevenstreet.ProductResponse

class ProductRepository(
    private val productRemoteDataSource: ProductRemoteDataSourceInterface,
    private val productLocalDataSource: ProductLocalDataSourceInterface
) : ProductRepositoryInterface {

    override fun getProductItem(
        keyWord: String,
        pageNum: Int,
        callback: (product: List<ProductResponse>) -> Unit
    ) {

        if (MyApplication.instance.context().isConnectedToNetwork()) {
            productRemoteDataSource.getProductList(keyWord, pageNum, callback)
        } else {
            productLocalDataSource.getProductList(callback)
        }


    }

    companion object {
        private var instance: ProductRepository? = null
        // lazy initialize
        fun getInstance(
            remoteDataSource: ProductRemoteDataSourceInterface,
            localDataSource: ProductLocalDataSourceInterface
        ): ProductRepository =
            instance ?: ProductRepository(
                remoteDataSource,
                localDataSource
            ).also {
                instance = it
            }
    }
}