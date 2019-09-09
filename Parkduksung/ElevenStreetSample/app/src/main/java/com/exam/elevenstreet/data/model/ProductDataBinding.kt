package com.exam.elevenstreet.data.model

import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.io.InputStream


class ProductDataBinding(inputStream: InputStream) {

    val productDataBindingList = ProductXmlPullParserHandler().parse(inputStream)

    fun getProductlist(): List<ProductResponse> {
        return productDataBindingList
    }


}
