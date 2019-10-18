package com.exam.elevenstreet.view.product.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter

import com.exam.elevenstreet.view.product.base.BaseView
import com.exam.elevenstreet.view.product.base.BasePresenter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*

class ProductPresenter : AppCompatActivity(), MainContract.Presenter {
    private lateinit var productList: List<ProductResponse>
//    private val productRepository by lazy {
//        ProductRepository(ProductLocalDataSource())
//    }
    override fun databind(): List<ProductResponse> {

        //val productAdapter = ProductRecyclerViewAdapter(productList)

        val inputStream = assets.open("ElevenStreetOpenApiService.xml")

        productList = ProductXmlPullParserHandler().parse(inputStream)

        return productList
    }

    override fun start() {


    }
    override fun setupView() {
        rv_product_list.adapter = ProductRecyclerViewAdapter()
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }

}