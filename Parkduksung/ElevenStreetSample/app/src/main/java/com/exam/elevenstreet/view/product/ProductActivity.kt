package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.Repository.ProductRepository
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity() {


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        dataBinding()
//        ProductDataBinding(assetsxmlFile, recyclerview_product)


//        val recyclerViewRun = recyclerView.run {
//
//            var adapter = ProductAdapter(productList as ArrayList<ProductResponse>)
//            recyclerView.adapter = adapter
//            layoutManager = LinearLayoutManager(this)
//
//        }


        val productList = ProductRepository().getProductlist("ElevenStreetOpenApiService.xml")


        recyclerview_product.run {
            var adapter =
                ProductAdapter(productList as ArrayList<ProductResponse>)
            recyclerview_product.adapter = adapter
            layoutManager = LinearLayoutManager(this@ProductActivity)
//        }


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

    }

    companion object {
        private const val TAG = "ProductActivity"
//TODO : 검색영역 작업하는데 사용할 변수
//        const val API_CODE = "ProductSearch"
    }
}
