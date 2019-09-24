package com.exam.elevenstreet.data.source.remote


import com.exam.elevenstreet.R
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL


class ProductRemoteDataSource : ProductRemoteData {
    private var elevenStreetApi: ElevenStreetApi? = null

    override fun getProductRemoteData(
        keyWord: String,
        callback: (productList: List<ProductResponse>) -> Unit
    ) {
        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val call = elevenStreetApi?.getProductList(
            App.instance.context().getString(R.string.eleven_street_API_KEY),
            API_CODE,
            keyWord,
            1
        )

        val url: String? = "${call?.request()?.url()}"


        if (url != null) {
            Thread(Runnable {
                val targetURL = URL(url)
                val inputStream = targetURL.openStream()
                ProductXmlPullParserHandler()
                    .parse(inputStream) { productList ->
                        callback(productList)
                    }
            }).start()
        }
    }


    companion object {
        const val API_CODE = "ProductSearch"
    }


}
