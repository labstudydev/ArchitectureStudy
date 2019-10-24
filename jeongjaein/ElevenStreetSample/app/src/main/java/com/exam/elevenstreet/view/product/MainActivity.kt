package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter
import com.exam.elevenstreet.view.product.adapter.Viewholder.ProductViewHolder
import com.exam.elevenstreet.view.product.presenter.MainContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MainContract.View, AppCompatActivity() {


    //    override lateinit var presenter: MainContract.Presenter
    private val repository = ProductRepository
    override var presenter: MainContract.Presenter = ProductPresenter(repository,this)
    private val productRecyclerViewAdapter by lazy { ProductRecyclerViewAdapter() }// 컴파일때 생성말고 접근할때 생성
    private val holder: ProductViewHolder? = null

    override fun showProduct(productList: List<ProductResponse>) {
        holder?.let { productRecyclerViewAdapter.onBindViewHolder(it, 1) }
        setupView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.databind()


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

