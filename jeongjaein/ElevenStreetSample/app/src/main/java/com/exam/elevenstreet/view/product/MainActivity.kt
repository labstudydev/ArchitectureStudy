package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductRecyclerViewAdapter
import com.exam.elevenstreet.view.product.presenter.MainContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MainContract.View, AppCompatActivity() {


    override lateinit var presenter: MainContract.Presenter

    private val productRecyclerViewAdapter by lazy { ProductRecyclerViewAdapter() }


    override fun showProduct(productList: List<ProductResponse>) {
        runOnUiThread {
            productRecyclerViewAdapter.addData(productList)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runOnUiThread {
            presenter = ProductPresenter(
                ProductRepository.getInstance(
                    ProductRemoteDataSource.getInstance(
                        RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")
                    ),
                    ProductLocalDataSource.getInstance()
                ), this
            )
        }

        setupView()


    }

    override fun setupView() {
        rv_product_list.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productRecyclerViewAdapter
        }

        search_button.setOnClickListener {
            productRecyclerViewAdapter.replaceAll()
            presenter.searchByKeyword("${search_src_text.text}")
        }
    }


    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }
}

