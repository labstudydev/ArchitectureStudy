package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter
import com.exam.elevenstreet.view.product.presenter.MainContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showProduct(productList: List<ProductResponse>) {

    }


    override var presenter: MainContract.Presenter = ProductPresenter()

    private val productRecyclerViewAdapter by lazy { ProductRecyclerViewAdapter() }// 컴파일때 생성말고 접근할때 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.databind()
        setupView()


    }


    override fun setupView() {
        rv_product_list.adapter = productRecyclerViewAdapter
        rv_product_list.layoutManager = LinearLayoutManager(this)
    }


    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }
}

