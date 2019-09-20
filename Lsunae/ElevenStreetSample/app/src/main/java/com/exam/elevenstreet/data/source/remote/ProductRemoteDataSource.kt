package com.exam.elevenstreet.data.source.remote

import android.os.AsyncTask
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.view.product.App
import com.exam.elevenstreet.view.product.ProductActivity
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL

class ProductRemoteDataSource()  {

    private var elevenStreetApi : ElevenStreetApi? = null

    private lateinit var keyWord : String
    private var pageNum = 1

//    private fun getSearchByKeyword(keyWord: String, pageNum: Int): List<ProductResponse> {
//
//        val context = App.instance.context()
//
//        RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")
//
//        val call = elevenStreetApi?.getProductList(
//            context.getString(com.exam.elevenstreet.R.string.eleven_street_API_KEY),
//            ProductActivity.API_CODE,
//            keyWord,
//            pageNum
//        )
//
//        val url: String? = "${call?.request()?.url()}"
//        val targetURL = URL(url)
//        val inputStream = targetURL.openStream()
//
//        return ProductXmlPullParserHandler().parse(inputStream)
//
//    }
//
//    inner class ProductTask: AsyncTask<String, String, List<ProductResponse>>() {
//
//        override fun doInBackground(vararg p0: String?): List<ProductResponse> {
//
//            return getSearchByKeyword(keyWord, pageNum)
//        }
//
//        override fun onPreExecute() {
//
//        }
//
//        override fun onProgressUpdate(vararg values: String?) {
////            super.onProgressUpdate(*values)
//        }
//
//        override fun onPostExecute(result: List<ProductResponse>?) {
////            super.onPostExecute(result)
//        }
//    }

}