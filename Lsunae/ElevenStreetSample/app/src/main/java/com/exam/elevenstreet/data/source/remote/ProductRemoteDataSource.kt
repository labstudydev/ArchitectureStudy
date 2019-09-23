package com.exam.elevenstreet.data.source.remote

import android.os.AsyncTask
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.App
import com.exam.elevenstreet.view.product.ProductActivity

import java.net.URL

class ProductRemoteDataSource {

    private var elevenStreetApi : ElevenStreetApi? = null

//    private lateinit var keyWord : String
//    private var pageNum = 1

    fun getSearchByKeyword(keyWord: String, pageNum: Int, callback: ProductCallback) {

//        this.keyWord = keyWord
//        this.pageNum = pageNum

        val list = ProductTask().execute(keyWord, "$pageNum").get()

        callback.onSuccess(list)
    }

    private inner class ProductTask: AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg params: String): List<ProductResponse> {

            val context = App.instance.context()

            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

            val call = elevenStreetApi?.getProductList(
                context.getString(com.exam.elevenstreet.R.string.eleven_street_API_KEY),
                ProductActivity.API_CODE,
                params[0],
                params[1].toInt()
            )

            val url: String? = "${call?.request()?.url()}"
            val targetURL = URL(url)
            val inputStream = targetURL.openStream()

            return ProductXmlPullParserHandler().parse(inputStream)
        }
    }
}