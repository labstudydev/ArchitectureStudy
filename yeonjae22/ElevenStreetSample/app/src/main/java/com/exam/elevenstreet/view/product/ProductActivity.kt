package com.exam.elevenstreet

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL


class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null
    val adapter = ProductAdapter()

    interface CallBack {
        fun onSuccess(productList1: List<ProductResponse>)
        fun onFailure(message: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val manager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(manager)
        recycler_view.setHasFixedSize(true)

        val productList = ProductLocalDataSource().getProductList(applicationContext)
        //val adapter = ProductAdapter()
        recycler_view.adapter = adapter
        adapter.addData(productList)

        btn_search.setOnClickListener {
            ProductTask().execute()
        }

    }

    fun getSearchByKeyword(
    ): List<ProductResponse> {
        val call = elevenStreetApi?.getProductList(
            getString(R.string.eleven_street_API_KEY),
            API_CODE,
            "${edt_search.text}",
            1
        )
        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        return productList
    }

    internal inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg keyWords: String): List<ProductResponse>? {
            val productList1 = getSearchByKeyword()
            return productList1
        }

        override fun onPostExecute(productList1: List<ProductResponse>) {
            adapter.addData(productList1)
        }
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}