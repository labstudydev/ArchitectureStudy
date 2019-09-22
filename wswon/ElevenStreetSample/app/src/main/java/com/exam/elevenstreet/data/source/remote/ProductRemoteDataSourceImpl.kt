package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.network.model.ProductResponse
import java.net.URL

class ProductRemoteDataSourceImpl private constructor(private val elevenStreetApi: ElevenStreetApi) : ProductRemoteDataSource  {

    override fun getProductList(keyWord: String, pageNum: Int, callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit) {
        val call = elevenStreetApi.getProductList(
            API_KEY,
            API_CODE,
            keyWord,
            pageNum
        )
        val url: String? = "${call?.request()?.url()}"

        Thread(Runnable {
            val targetURL = URL(url)
            val inputStream = targetURL.openStream()
            ProductXmlPullParserHandler()
                .parse(inputStream) { productList, totalCount ->
                   callback(productList, totalCount)
                }
        }).start()
    }

    companion object{
        private const val API_KEY = "1419942f67713eca9cfbb9a752cac395"
        private const val API_CODE = "ProductSearch"

        private var instance: ProductRemoteDataSourceImpl? = null
        fun getInstance(elevenStreetApi: ElevenStreetApi): ProductRemoteDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: ProductRemoteDataSourceImpl(elevenStreetApi).also {
                    instance = it
                }
            }
    }
}