package com.exam.elevenstreet.data

import android.content.Context
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource private constructor() {
    val context: Context = App.instance.context()

    fun loadCacheProductData(callback: ProductRepository.CallBack) {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))
        ProductXmlPullParserHandler().parse(inputStream)
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