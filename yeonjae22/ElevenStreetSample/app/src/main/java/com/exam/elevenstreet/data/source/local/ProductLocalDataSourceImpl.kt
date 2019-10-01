package com.exam.elevenstreet.data.source.local

import android.content.Context
import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSourceImpl private constructor() : ProductLocalDataSource {
    val context: Context = App.instance.context()

    override fun loadCacheProductData(callback: ProductCallBack) {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))
        ProductXmlPullParserHandler().parse(inputStream)
    }

    companion object {
        private var instance: ProductLocalDataSourceImpl? = null
        fun getInstance(): ProductLocalDataSourceImpl =
            instance
                ?: ProductLocalDataSourceImpl().also {
                    instance = it
                }
    }
}