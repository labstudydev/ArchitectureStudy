package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun showProduct(productList: List<ProductResponse>) {
        runOnUiThread {
            productRecyclerViewAdapter.addData(productList)
        }

    }

    override fun setupView() {
        rv_product_list.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productRecyclerViewAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                        if(!recyclerView.canScrollVertically(1)){
                            adapter?.itemCount?.let { presenter.nextProduct(it) }
                        }

                }
            })
        }
        search_button.setOnClickListener {
            productRecyclerViewAdapter.replaceAll()
            presenter.searchByKeyword("${search_src_text.text}")
        }
    }

    override fun endDataLoad() {
        runOnUiThread{
            presenter.checkProductEnd(productRecyclerViewAdapter.itemCount)
        }
    }


    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }
}

