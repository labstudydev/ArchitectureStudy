package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.App
import com.exam.elevenstreet.view.product.App.Companion.instance

class ProductLocalDataSource {

    val context = App.instance.context()

    fun getProductDataList(callback: ProductCallback) {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))

    }

    companion object {
        private var instance: ProductLocalDataSource? = null
        fun getInstance(): ProductLocalDataSource =
            instance ?: ProductLocalDataSource().also {
                instance = it
            }
    }
}