package com.exam.elevenstreet.view.product

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import kotlinx.android.synthetic.main.activity_product.*


class ProductActivity : AppCompatActivity(), ProductContract.View {

    override lateinit var presenter: ProductContract.Presenter

    private val productAdapter by lazy { ProductAdapter() }

    private var isShowingMessage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        presenter = ProductPresenter(
            ProductRepositoryImpl.getInstance(
                ProductRemoteDataSourceImpl.getInstance(
                    RetrofitInstance.getInstance(CALL_URL)
                ),
                ProductLocalDataSourceImpl.getInstance()
            ),
            this
        ).apply {
            start()
        }

        setupView()
    }

    override fun showProductList(item: ProductItem) {
        runOnUiThread {
            productAdapter.addData(item)
            tv_product_count.text = "${productAdapter.itemCount} 개"
        }
    }

    override fun showMessage(message: String) {
        if (!isShowingMessage){
            isShowingMessage = true
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                isShowingMessage = false
            },1000)
        }
    }

    override fun showLoadingProgress() {
        runOnUiThread {
            pgr_product_load.visibility = View.VISIBLE
            pgr_product_load.isIndeterminate = true
        }
    }

    override fun endDataLoad() {
        runOnUiThread {
            pgr_product_load.visibility = View.GONE
            pgr_product_load.isIndeterminate = false
            presenter.checkProductEnd(productAdapter.itemCount)
        }
    }

    private fun setupView() {
        rv_product.run {
            layoutManager = LinearLayoutManager(this@ProductActivity)
            adapter = productAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.loadNextProduct(productAdapter.itemCount)
                    }
                }
            })
        }

        btn_search.setOnClickListener {
            productAdapter.clearListData()
            presenter.searchByKeyword("${edt_input_keyword.text}")
        }
    }


    companion object {
        private const val CALL_URL = "https://openapi.11st.co.kr/openapi/"
        private const val TAG = "ProductActivity"
    }
}
