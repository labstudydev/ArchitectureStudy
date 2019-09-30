package com.exam.elevenstreet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R.layout.activity_main
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        dataBinding()
        var product: List<ProductResponse>?
        rv_product_list.adapter = ProductRecyclerViewAdapter(product)
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }

    private fun dataBinding(): List<ProductResponse> {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        productList.forEach {
            Log.d(TAG, "$it")
        }
        return productList
    }

    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }
}