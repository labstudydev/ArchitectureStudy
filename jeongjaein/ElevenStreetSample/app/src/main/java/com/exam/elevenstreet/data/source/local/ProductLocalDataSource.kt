package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.MyApplication
import com.example.elevenstreet.ProductXmlPullParserHandler
import com.exam.elevenstreet.data.Callback
import com.example.elevenstreet.ProductResponse

class ProductLocalDataSource : ProductLocalDataSourceInterface {


    override fun getProductList(callback: (productList: List<ProductResponse>) -> Unit) {

        val inputStream =
            MyApplication.instance.context().assets.open("ElevenStreetOpenApiService.xml")

        callback(ProductXmlPullParserHandler().parse(inputStream))
    }


    companion object {
        private var instance: ProductLocalDataSource? = null
        fun getInstance(): ProductLocalDataSource =
            instance ?: ProductLocalDataSource().also {
                instance = it
            }

    }

}