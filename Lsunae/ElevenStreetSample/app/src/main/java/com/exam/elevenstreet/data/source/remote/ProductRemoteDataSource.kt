package com.exam.elevenstreet.data.source.remote

import android.os.AsyncTask
import com.exam.elevenstreet.R
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.App
import com.exam.elevenstreet.view.product.ProductActivity

import java.net.URL

class ProductRemoteDataSource constructor(private var elevenStreetApi: ElevenStreetApi) {
    private val context = App.instance.context()

    fun getSearchByKeyword(keyWord: String, callback: ProductCallback) {

        callback.onSuccess(ProductTask().execute(keyWord).get())
    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg keyWord: String): List<ProductResponse>? {

            val call = elevenStreetApi?.getProductList(
                context.getString(R.string.eleven_street_API_KEY),
                ProductActivity.API_CODE,
                keyWord[0],
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
            instance ?: ProductRemoteDataSource(elevenStreetApi).also {
                instance = it
            }
    }
}