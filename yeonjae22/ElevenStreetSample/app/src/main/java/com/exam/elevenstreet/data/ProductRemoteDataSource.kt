package com.exam.elevenstreet.data

import android.content.Context
import android.os.AsyncTask
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.ProductActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSource() {

    interface CallBack {
        fun onSuccess(productList: List<ProductResponse>)
        fun onFailure(message: String)
    }

    private var elevenStreetApi: ElevenStreetApi? = null
    private val context: Context = App.instance.context()

    fun getSearchByKeyword(
        keyword: String
    ): List<ProductResponse> {
        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val call = elevenStreetApi?.getProductList(
            context.getString(R.string.eleven_street_API_KEY),
            ProductActivity.API_CODE,
            keyword,
            1
        )
        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()
        return ProductXmlPullParserHandler().parse(inputStream)
    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg keyWords: String): List<ProductResponse>? {
            val productList = getSearchByKeyword(keyWords[0])
            return productList
        }

//        fun onPostExecute(productList: List<ProductResponse>, callback: CallBack) {
//            if (callback != null) {
//                callback.onSuccess(productList)
//            } else {
//                callback.onFailure("err")
//            }
//        }
    }
}