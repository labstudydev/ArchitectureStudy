package com.exam.elevenstreet.data

import android.content.Context
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource() {
    val context: Context = App.instance.context()

    fun getProductList(callback: ProductRepository.CallBack): List<ProductResponse> {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))
        return ProductXmlPullParserHandler().parse(inputStream)
    }
}