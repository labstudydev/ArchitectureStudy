package com.exam.elevenstreet.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBinding()
    }

    private fun dataBinding() {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        val productList = ProductXmlPullParserHandler().parse(inputStream)

        val adapter = ProductAdapter(productList as ArrayList<ProductResponse>)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        private const val TAG = "MainActivity"
//TODO : 검색영역 작업하는데 사용할 변수
//        const val API_CODE = "ProductSearch"
    }
}
