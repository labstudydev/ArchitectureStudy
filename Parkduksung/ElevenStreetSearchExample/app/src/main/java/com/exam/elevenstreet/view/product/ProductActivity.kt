package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.ElevenStreetApi
import com.exam.elevenstreet.R
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL


class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null

    private val handler = Handler()
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val productList = ProductRepository().getProductlist("ElevenStreetOpenApiService.xml")
//
//
//        recyclerview_product.run {
//            var adapter =
//                ProductAdapter(productList as ArrayList<ProductResponse>)
//            recyclerview_product.adapter = adapter
//            layoutManager = LinearLayoutManager(this@ProductActivity)
//
//        }
        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")
        val call = elevenStreetApi?.getProductList(
            getString(R.string.eleven_street_API_KEY),
            API_CODE,
            "햇반",
            1
        )


        val url: String? = "${call?.request()?.url()}"

        url?.let {
            if (url != null) {

                htmlParser(url)
            }

        }
    }


    private fun htmlParser(url1: String) {


        Thread(Runnable {
            val url = URL(url1)

            val inputStream = url.openStream()
            val productList = ProductXmlPullParserHandler().parse(inputStream)

            Log.d("asdfasdf","${productList.size}")


            recyclerview_product.run {
                var adapter =
                    ProductAdapter(productList as ArrayList<ProductResponse>)
                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@ProductActivity)

            }

        }).start()
    }


    companion object {
        private const val TAG = "ProductActivity"
        //TODO : 검색영역 작업하는데 사용할 변수
        const val API_CODE = "ProductSearch"
    }
}
