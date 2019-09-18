package com.exam.elevenstreet.data

import android.content.Context
import android.os.AsyncTask
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.ProductActivity
import com.exam.elevenstreet.ProductAdapter
import com.exam.elevenstreet.R
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL

class ProductRemoteDataSource() {
    private var elevenStreetApi: ElevenStreetApi? = null
    //val adapter = ProductAdapter()
    val context: Context = App.instance.context()

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
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        return productList
    }

//    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {
//
//        override fun doInBackground(vararg keyWords: String): List<ProductResponse>? {
//            val productList1 = getSearchByKeyword(keyWords[0])
//            return productList1
//        }
//
//        override fun onPostExecute(productList1: List<ProductResponse>) {
//            adapter.addData(productList1)
//        }
//    }

}