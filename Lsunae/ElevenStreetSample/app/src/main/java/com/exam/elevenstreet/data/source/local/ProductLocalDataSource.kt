package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.App.Companion.instance

class ProductLocalDataSource {

    fun productData() : List<ProductResponse>{
        val context = instance.context()
        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
        return ProductXmlPullParserHandler().parse(inputStream)
    }

}