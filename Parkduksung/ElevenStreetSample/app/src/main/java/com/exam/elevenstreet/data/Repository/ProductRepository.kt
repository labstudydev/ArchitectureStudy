package com.exam.elevenstreet.data.Repository

import android.content.Context
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.io.InputStream


class ProductRepository() {

    fun getProductlist(filename: String): List<ProductResponse> {
        val context: Context = App.instance.context()
        val inputStream: InputStream = context.assets.open(filename)
        val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)

        return productRepositoryList
    }


}
