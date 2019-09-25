package com.exam.elevenstreet.data.source.remote


import com.exam.elevenstreet.R
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSourceImpl private constructor(private val elevenStreetApi: ElevenStreetApi) {

    fun getProductRemoteData(
        keyword: String,
        callback: ProductRemoteDataSource
    ) {

        val call = elevenStreetApi.getProductList(
            App.instance.context().getString(R.string.eleven_street_API_KEY),
            API_CODE,
            keyword,
            1
        )

        val url: String? = "${call.request()?.url()}"


        if (url != null) {
            Thread(Runnable {
                val targetURL = URL(url)
                val inputStream = targetURL.openStream()
                callback.getProductRemoteData(
                    keyword, ProductXmlPullParserHandler()
                        .parse(inputStream)
                )
            }).start()
        }
    }

    companion object {
        const val API_CODE = "ProductSearch"

        private var instance: ProductRemoteDataSourceImpl? = null

        fun getInstance(elevenStreetApi: ElevenStreetApi): ProductRemoteDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: ProductRemoteDataSourceImpl(elevenStreetApi).also {
                    instance = it
                }
            }

    }

}
