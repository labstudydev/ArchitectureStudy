package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL

class ProductRemoteDataSource(){

    private var elevenStreetApi: ElevenStreetApi? = null

    interface CallBack {
        fun onSuccess(productList: List<ProductResponse>?)
        fun onFailure(message: String)
    }

    fun getProductlist(
        inputKeyword: String,
        callback: CallBack
    ) {

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")
        val call = elevenStreetApi?.getProductList(
            API_KEY,
            API_CODE,
            inputKeyword,
            1
        )

        val url: String? = "${call?.request()?.url()}"


        if (url != null) {
            Thread(Runnable {
                val targetURL = URL(url)
                val inputStream = targetURL.openStream()
                callback.onSuccess(ProductXmlPullParserHandler().parse(inputStream))
            }).start()
        } else {
            callback.onFailure("url 오류")
        }


    }


    companion object {

        const val API_CODE = "ProductSearch"
        const val API_KEY = "1419942f67713eca9cfbb9a752cac395"
    }


}
