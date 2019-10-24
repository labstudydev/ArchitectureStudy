package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.MyApplication
import com.example.elevenstreet.ProductXmlPullParserHandler
import com.exam.elevenstreet.data.CallBack

class ProductLocalDataSource : ProductLocalDataSourceInterface {

    override fun getProductList(callback: CallBack) {

        val inputStream =
            MyApplication.instance.context().assets.open("ElevenStreetOpenApiService.xml")

        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream,callback))
    }


    companion object {
        private var instance: ProductLocalDataSource? = null
        fun getInstance(): ProductLocalDataSource =
                instance ?: ProductLocalDataSource().also {
                    instance = it
                }

    }

}