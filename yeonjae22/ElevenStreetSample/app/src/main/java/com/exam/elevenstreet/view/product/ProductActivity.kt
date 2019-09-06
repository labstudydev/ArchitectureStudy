package com.exam.elevenstreet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(manager)
        recycler_view.setHasFixedSize(true)
        dataBinding()
    }

    private fun dataBinding() {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)
        val adapter = ProductAdapter(productList)
        recycler_view.adapter= adapter
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}
