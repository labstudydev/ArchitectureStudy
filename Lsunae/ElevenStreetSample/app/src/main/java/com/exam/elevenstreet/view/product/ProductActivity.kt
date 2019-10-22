package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import androidx.databinding.DataBindingUtil
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityMainBinding
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productPresenter: ProductContract.Presenter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        productAdapter = ProductAdapter(mutableListOf())

        productAdapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productItem: ProductResponse) {
                
            }
        })
        productPresenter = ProductPresenter(
            this, ProductRepositoryImpl(
                ProductRemoteDataSourceImpl.getInstance(RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")),
                ProductLocalDataSourceImpl.getInstance()
            )
        )
        setupView()

        activityMainBinding.btnSearch.setOnClickListener {
            productPresenter.searchByKeyword("${activityMainBinding.editSearch.text}")
        }
    }

    override fun showProductList(productList: List<ProductResponse>) {
        productAdapter.replaceAll(productList)
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(this@ProductActivity)
        activityMainBinding.recyclerViewProduct.layoutManager = layoutManager
        activityMainBinding.recyclerViewProduct.adapter = productAdapter
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}