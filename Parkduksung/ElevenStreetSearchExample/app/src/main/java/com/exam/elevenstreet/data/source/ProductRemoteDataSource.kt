package com.exam.elevenstreet.data.source

import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.HttpURLConnection
import java.net.URL

class ProductRemoteDataSource{

    fun getProductlist1(url: String): List<ProductResponse> {


        val targetURL = URL(url)
        val connectUrl = targetURL.openConnection() as HttpURLConnection
        val inputStream = connectUrl.inputStream
        val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)

        return productRepositoryList
    }

    fun getProductlist3(url: String): String {

        val targetURL = URL(url).openConnection()
        val data = targetURL.getInputStream().bufferedReader().readText()
//        val inputStream = targetURL.openStream()
//        val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)
        return data

    }

}