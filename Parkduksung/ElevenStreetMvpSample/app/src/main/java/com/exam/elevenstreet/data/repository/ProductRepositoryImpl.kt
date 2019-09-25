package com.exam.elevenstreet.data.repository


import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse

class ProductRepositoryImpl private constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {


    override fun getProductRepositoryLocalData(callback: (productList: List<ProductResponse>) -> Unit) {
        productLocalDataSource.getProductLocalData(callback)
    }

    override fun getProductRepositoryRemoteData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    ) {
        if (App.instance.context().isConnectedToNetwork()) {
            productRemoteDataSource.getProductRemoteData(keyWord, callback)
        } else {
            productLocalDataSource.getProductLocalData(callback)
        }

    }


    companion object {

        private var instance: ProductRepository? = null
        fun getInstance(
            remoteDataSource: ProductRemoteDataSource,
            localDataSource: ProductLocalDataSource
        ): ProductRepository =
            instance ?: synchronized(this) {
                instance ?: ProductRepositoryImpl(remoteDataSource, localDataSource).also {
                    instance = it
                }
            }
    }


}

