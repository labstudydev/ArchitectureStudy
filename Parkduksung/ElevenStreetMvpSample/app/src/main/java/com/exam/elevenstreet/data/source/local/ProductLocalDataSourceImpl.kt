package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSourceImpl private constructor() {

    fun getProductLocalData(callback: ProductLocalDataSource) {

        val inputStream = App.instance.context().assets.open("ElevenStreetOpenApiService.xml")
        callback.getProductLocalData(ProductXmlPullParserHandler().parse(inputStream))

    }


    companion object {

        private var instance: ProductLocalDataSourceImpl? = null

        fun getInstance(): ProductLocalDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: ProductLocalDataSourceImpl().also {
                    instance = it
                }
            }


    }

}