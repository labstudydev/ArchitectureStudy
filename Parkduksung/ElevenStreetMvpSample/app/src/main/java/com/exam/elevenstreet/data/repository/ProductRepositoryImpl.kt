package com.exam.elevenstreet.data.repository


import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse

class ProductRepositoryImpl private constructor(
    private val productRemoteDataSourceImpl: ProductRemoteDataSourceImpl,
    private val productLocalDataSourceImpl: ProductLocalDataSourceImpl
) {


    fun getProductRepositoryLocalData(callback: ProductRepository) {

        productLocalDataSourceImpl.getProductLocalData(object :
            ProductLocalDataSource {
            override fun getProductLocalData(productList: List<ProductResponse>) {
                callback.getProductRepositoryLocalData(productList)
            }
        })


    }

    fun getProductRepositoryRemoteData(
        keyword: String,
        callback: ProductRepository
    ) {
        if (App.instance.context().isConnectedToNetwork()) {
            productRemoteDataSourceImpl.getProductRemoteData(
                keyword,
                object : ProductRemoteDataSource {
                    override fun getProductRemoteData(
                        keyword: String,
                        productList: List<ProductResponse>
                    ) {
                        callback.getProductRepositoryRemoteData(keyword, productList)
                    }

                })


        } else {
            ProductLocalDataSourceImpl.getInstance().getProductLocalData(object :
                ProductLocalDataSource {
                override fun getProductLocalData(productList: List<ProductResponse>) {
                    callback.getProductRepositoryLocalData(productList)
                }
            })
        }

    }


    companion object {


        private var instance: ProductRepositoryImpl? = null
        fun getInstance(
            remoteDataSourceImpl: ProductRemoteDataSourceImpl,
            localDataSourceImpl: ProductLocalDataSourceImpl
        ): ProductRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: ProductRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl).also {
                    instance = it
                }
            }
    }


}

