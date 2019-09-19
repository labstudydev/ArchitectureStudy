package com.exam.elevenstreet.data.source.local

import android.content.Context
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.io.InputStream

class ProductLocalDataSource {

    fun getProductlist(filename: String, callback: (productList: List<ProductResponse>) -> Unit) {
        val context: Context = App.instance.context()
        val inputStream: InputStream = context.assets.open(filename)
        ProductXmlPullParserHandler().parse(inputStream) { productList ->
            callback(productList)
        }

    }
}