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

    lateinit var productList: List<ProductResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        dataBinding()
//        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
//        val productList = ProductXmlPullParserHandler().parse(inputStream)
        rv_product_list.adapter = ProductRecyclerViewAdapter(productList)
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }

    private fun dataBinding(): List<ProductResponse> {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        productList = ProductXmlPullParserHandler().parse(inputStream)
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