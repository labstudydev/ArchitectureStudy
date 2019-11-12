package com.exam.elevenstreet.data.source.remote


import com.exam.elevenstreet.network.ElevenStreetApi
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSource(private val api: ElevenStreetApi) : ProductRemoteDataSourceInterface {

    override fun getProductList(
        keyWord: String,
        pageNum: Int,
        callback: (productList: List<ProductResponse>) -> Unit
    ) {
        val call = api.getProductList(
            APIKEY,
            APICODE,
            keyWord,
            pageNum
        )

        var thread = Thread(Runnable {
            val targetURL = URL("${call.request().url()}")
            val inputStream = targetURL.openStream()
            callback(ProductXmlPullParserHandler().parse(inputStream))
        })
        thread.start()

    }

    companion object {
        const val APIKEY = "914cbc30d94291e219043cfd76ab6916"
        const val APICODE = "ProductSearch"

        private var instance: ProductRemoteDataSource? = null

        fun getInstance(elevenStreetApi: ElevenStreetApi): ProductRemoteDataSource =

            instance ?: ProductRemoteDataSource(elevenStreetApi).also {
                instance = it
            }


    }
}