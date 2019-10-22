package com.exam.elevenstreet.data.source.remote

import android.content.Context
import android.os.AsyncTask
import com.exam.elevenstreet.view.product.ProductActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.network.ElevenStreetApi
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSourceImpl private constructor(private var elevenStreetApi: ElevenStreetApi) :
    ProductRemoteDataSource {

    private val context: Context = App.instance.context()

    override fun getSearchByKeyword(
        keyword: String,
        callback: ProductCallBack
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
        private var instance: ProductRemoteDataSourceImpl? = null
        fun getInstance(elevenStreetApi: ElevenStreetApi): ProductRemoteDataSourceImpl =
            instance
                ?: ProductRemoteDataSourceImpl(elevenStreetApi).also {
                    instance = it
                }
    }
}