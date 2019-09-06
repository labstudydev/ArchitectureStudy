package com.exam.elevenstreet.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.ProductDataBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val assetsxmlFile = assets.open("ElevenStreetOpenApiService.xml")
//        dataBinding()
        ProductDataBinding(assetsxmlFile,recyclerview_product)



    }

//    private fun dataBinding() {
//        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
//        val productList = ProductXmlPullParserHandler().parse(inputStream)
//
//
//        var adapter = ProductAdapter(productList as ArrayList<ProductResponse>)
//
////        TODO 방법 1
////        recyclerview_product.adapter = adapter
////        recyclerview_product.layoutManager = LinearLayoutManager(this)
//
//
//        //TODO 우석조언.
//        recyclerview_product.run {
//            recyclerview_product.adapter = adapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//
//
//    }

    companion object {
        private const val TAG = "MainActivity"
//TODO : 검색영역 작업하는데 사용할 변수
//        const val API_CODE = "ProductSearch"
    }
}
