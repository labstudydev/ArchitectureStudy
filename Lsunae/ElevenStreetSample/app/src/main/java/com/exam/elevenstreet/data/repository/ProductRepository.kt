package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.App
import com.exam.elevenstreet.view.product.ProductActivity
import com.exam.elevenstreet.view.product.ProductActivity.ProductTask

class ProductRepository(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) {

    fun getProductList(){
        if (){  //network
            productRemoteDataSource.getSearchByKeyword("수건", 1, object : ProductCallback{
                override fun onSuccess(productList: List<ProductResponse>) {
                    productList
                }
            })
        }
        else{
            productLocalDataSource
        }
    }
}