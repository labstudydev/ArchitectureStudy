package com.exam.elevenstreet.data

import android.content.Context
import android.os.AsyncTask
import com.ElevenStreetApi
import com.exam.elevenstreet.ProductActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSource private constructor(private var elevenStreetApi: ElevenStreetApi) {

    private val context: Context = App.instance.context()

    fun getSearchByKeyword(
        keyword: String,
        callback: ProductRepository.CallBack
    ) {
        callback.onSuccess(ProductTask().execute(keyword).get())
    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg keyWords: String): List<ProductResponse>? {
            val call = elevenStreetApi?.getProductList(
                context.getString(R.string.eleven_street_API_KEY),
                ProductActivity.API_CODE,
                keyWords[0],
                1
            )
            val url: String? = "${call?.request()?.url()}"
            val targetURL = URL(url)
            val inputStream = targetURL.openStream()

            return ProductXmlPullParserHandler().parse(inputStream)
        }
    }

    companion object {
        private var instance: ProductRemoteDataSource? = null
        fun getInstance(elevenStreetApi: ElevenStreetApi): ProductRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: ProductRemoteDataSource(elevenStreetApi).also {
                    instance = it
                }
            }
    }
}