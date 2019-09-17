package com.exam.elevenstreet.data

import android.content.Context
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource() {
    val context: Context = App.instance.context()

    fun getProductList(): List<ProductResponse> {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        return productList
    }
}