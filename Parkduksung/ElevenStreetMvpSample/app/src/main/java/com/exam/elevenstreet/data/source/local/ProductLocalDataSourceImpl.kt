package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSourceImpl private constructor() : ProductLocalDataSource {
    override fun getLocalData(callback: ProductLocalDataSourceCallback) {
        val inputStream = App.instance.context().assets.open("ElevenStreetOpenApiService.xml")

        if (App.instance.context().assets != null) {
            callback.onFailure("Get Data Fail!!")
        } else {
            callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))
        }

    }

    companion object {

        private var instance: ProductLocalDataSourceImpl? = null

        fun getInstance(): ProductLocalDataSourceImpl =
            instance ?: instance ?: ProductLocalDataSourceImpl().also {
                instance = it
            }
    }

}