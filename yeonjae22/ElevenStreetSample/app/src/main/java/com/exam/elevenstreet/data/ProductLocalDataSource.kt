package com.exam.elevenstreet.data

import android.content.Context
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource() {

    fun getProductList(context: Context): List<ProductResponse> {
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        return productList
    }
}