package com.exam.elevenstreet.view.product

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter
import com.exam.elevenstreet.view.product.presenter.MainContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),MainContract.View{


//    private lateinit var productList: List<ProductResponse>
    private val productRepository by lazy {
        ProductRepository(ProductLocalDataSource())
    }
    override lateinit var presenter : MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProductPresenter()
//        dataBinding()

//        productRepository


        search_button.setOnClickListener {
            val productAdapter = ProductRecyclerViewAdapter()

//            productAdapter.setOnItemClickListener(object :
//                ProductRecyclerViewAdapter.OnItemClickListener {
//                override fun onClick() {
//
//                }
//            })
            setupView()
            presenter.databind()

        }

    }

    private fun setupView() {
        rv_product_list.adapter = ProductRecyclerViewAdapter()
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }

//    private fun dataBinding(): List<ProductResponse> {
//        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
//
//        productList = ProductXmlPullParserHandler().parse(inputStream)
//
//        return productList
//
//    }


    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }
}

//https://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=914cbc30d94291e219043cfd76ab6916&apiCode=ProductSearch&keyword=(여기에 컴색키워드) 나중에 쓸거같음