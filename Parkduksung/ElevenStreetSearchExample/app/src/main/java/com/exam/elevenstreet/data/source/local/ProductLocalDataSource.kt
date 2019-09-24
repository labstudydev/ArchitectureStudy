package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler

class ProductLocalDataSource : ProductLocalData {
    override fun getProductLocalData(callback: (productList: List<ProductResponse>) -> Unit) {
        val inputStream = App.instance.context().assets.open("ElevenStreetOpenApiService.xml")
        ProductXmlPullParserHandler().parse(inputStream) { productList ->
            callback(productList)
        }
    }

}