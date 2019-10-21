package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.MyApplication
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource() : ProductLocalDataSourceInterface {

    override fun getProductList(callback: (productList: List<ProductResponse>) -> Unit) {

        val inputStream =
            MyApplication.instance.context().assets.open("ElevenStreetOpenApiService.xml")

        ProductXmlPullParserHandler().parse(inputStream) { productList ->
            callback(productList)
        }
    }

    companion object {
        private var instance: ProductLocalDataSource? = null
        fun getInstance(): ProductLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: ProductLocalDataSource().also {
                    instance = it
                }
            }
    }

}