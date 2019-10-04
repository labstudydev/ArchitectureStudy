package com.exam.elevenstreet.data.repository


import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceCallback
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceCallback
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse

class ProductRepositoryImpl private constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {
    override fun getSearchByKeyword(keyword: String, callback: ProductRepositoryCallback) {

        if (App.instance.context().isConnectedToNetwork()) {


            productRemoteDataSource.getRemoteData(keyword,
                object : ProductRemoteDataSourceCallback {
                    override fun getProductList(productList: List<ProductResponse>) {
                        if (productList.isNotEmpty()) {
                            callback.onSuccess(productList)
                        } else {
                            callback.onFailure("productList is null")
                        }
                    }
                })

        } else {

            productLocalDataSource.getLocalData(object : ProductLocalDataSourceCallback {
                override fun getProductList(productList: List<ProductResponse>) {
                    if (productList.isNotEmpty()) {
                        callback.onSuccess(productList)
                    } else {
                        callback.onFailure("productList is null")
                    }
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
            instance ?: ProductRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl).also {
                instance = it
            }

    }


}

