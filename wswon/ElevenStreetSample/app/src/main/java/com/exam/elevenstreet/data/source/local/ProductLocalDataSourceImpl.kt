package com.exam.elevenstreet.data.source.local

import android.content.Context
import androidx.core.content.ContextCompat
import com.exam.elevenstreet.App
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.network.model.ProductResponse

class ProductLocalDataSourceImpl private constructor() : ProductLocalDataSource {


    override fun getSampleProductList(callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit) {
        val inputStream = App.instance.context().assets.open("sample.xml")
        ProductXmlPullParserHandler().parse(inputStream){ productList, totalCount ->
            callback(productList, totalCount)
        }
    }

    companion object {
        private var instance: ProductLocalDataSourceImpl? = null
        fun getInstance(): ProductLocalDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: ProductLocalDataSourceImpl().also {
                    instance = it
                }
            }
    }
}