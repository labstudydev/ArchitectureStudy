package com.exam.elevenstreet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.data.ProductLocalDataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val manager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(manager)
        recycler_view.setHasFixedSize(true)

        val productList = ProductLocalDataSource().getProductList(applicationContext)
        val adapter = ProductAdapter()
        recycler_view.adapter = adapter
        adapter.addData(productList)

    }

    fun getSearchByKeyword(keyWords: String, pageNumber: Int) {
        val call = elevenStreetApi?.getProductList(
            getString(R.string.eleven_street_API_KEY),
            API_CODE,
            "${edt_search.text}",
            1
        )
        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}