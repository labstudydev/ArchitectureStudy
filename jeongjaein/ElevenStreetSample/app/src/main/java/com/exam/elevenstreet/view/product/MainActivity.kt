package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter
import com.exam.elevenstreet.view.product.presenter.MainContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showProduct() {

    }

//    private val productRepository by lazy {
//        ProductRepositoryInterface(ProductLocalDataSource())
//    }


    override var presenter: MainContract.Presenter = ProductPresenter()

    private val productRecyclerViewAdapter by lazy { ProductRecyclerViewAdapter() }// 컴파일때 생성말고 접근할때 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        presenter.databind()
    }
//        1. context가 필요한 작업을 미리해서 넘겨준다 ex) presenter == ProductPresenter(this@MainActivity,inputstream)
//        dataBinding()
//        2. global context를 만들어 사용한다. asset열때 ㅇㅅㅇ
//        productRepository


//        search_button.setOnClickListener {

//                        productAdapter.setOnItemClickListener(object :
//                ProductRecyclerViewAdapter.OnItemClickListener {
//                override fun onClick() {
//
//                }
//            })


//        }



    override fun setupView() {
        rv_product_list.adapter = productRecyclerViewAdapter
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