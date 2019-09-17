package com.exam.elevenstreet

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.exam.elevenstreet.data.ProductRemoteDataSource
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*

class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null
    val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val manager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(manager)
        recycler_view.setHasFixedSize(true)

        val productList = ProductLocalDataSource().getProductList()
        recycler_view.adapter = adapter
        adapter.addData(productList)

        btn_search.setOnClickListener {
            ProductTask().execute("${edt_search.text}")
        }

    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg keyWords: String): List<ProductResponse>? {
            val productList1 = ProductRemoteDataSource().getSearchByKeyword(keyWords[0])
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