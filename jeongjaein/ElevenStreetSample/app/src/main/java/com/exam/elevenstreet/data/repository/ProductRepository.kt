package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.CallBack
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.example.elevenstreet.ProductResponse

class ProductRepository(
    val productLocalDataSource: ProductLocalDataSource

) : ProductRepositoryInterface {

    override fun getProductItem(callback: CallBack) {


//        ProductLocalDataSource.getInstance()
//            .getProductList { callback: List<ProductResponse> -> Unit }

        productLocalDataSource.getProductList(callback)
    }


    private var instance: ProductRepository? = null
    // lazy initialize
    fun getInstance(
        localDataSource: ProductLocalDataSource
    ) {
    }

}