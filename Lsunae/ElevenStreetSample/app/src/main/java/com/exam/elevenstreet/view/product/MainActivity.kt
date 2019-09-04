package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.view.product.adapter.ListAdapter
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.list_view.*


class MainActivity : AppCompatActivity() {

    //var ProductList = arrayListOf<ProductResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)

        val Adapter = ListAdapter(this, productList)
        list_view.adapter = Adapter

        //dataBinding()

    }

    private fun dataBinding() {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)

    }

    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }

}
