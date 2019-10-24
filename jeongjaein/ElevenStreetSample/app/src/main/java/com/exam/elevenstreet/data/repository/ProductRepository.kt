package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.CallBack
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.example.elevenstreet.ProductResponse

class ProductRepository(
    private val productLocalDataSource: ProductLocalDataSource

) : ProductRepositoryInterface {

    override fun getProductItem(callback: CallBack) {



        productLocalDataSource.getProductList(callback)
    }


    companion object{
        private var instance: ProductRepository? = null
        // lazy initialize
        fun getInstance(
//            localDataSource: ProductLocalDataSource
        callback: CallBack

        ) {

            //추가적으로 구현
        }

    }

}