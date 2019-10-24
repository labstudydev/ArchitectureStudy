package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.view.product.App

class ProductLocalDataSourceImpl : ProductLocalDataSource {

    private val context = App.instance.context()

    override fun getProductDataList(callback: ProductCallback) {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))

    }

    companion object {
        private var instance: ProductLocalDataSourceImpl? = null
        fun getInstance(): ProductLocalDataSourceImpl =
            instance ?: ProductLocalDataSourceImpl().also {
                instance = it
            }
    }
}