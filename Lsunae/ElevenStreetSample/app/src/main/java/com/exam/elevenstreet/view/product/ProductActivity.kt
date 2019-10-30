package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.ActivityMainBinding
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.base.BaseActivity
import com.exam.elevenstreet.viewmodel.ProductViewModel
import com.exam.elevenstreet.viewmodel.factory.ProductViewModelFactory

class ProductActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var productAdapter: ProductAdapter

    private val productViewModel by lazy {
        ViewModelProviders
            .of(this@ProductActivity, ProductViewModelFactory())
            .get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productAdapter = ProductAdapter(mutableListOf())
        productAdapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productItem: ProductResponse) {

            }
        })
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(this@ProductActivity)

        binding.recyclerViewProduct.run {
            this.layoutManager = layoutManager
            adapter = productAdapter
        }

        binding.btnSearch.setOnClickListener {
            productViewModel.searchByKeyword("${binding.editSearch.text}")
        }
    }

    private fun setupViewModel() {
        productViewModel.productItemList.observe(this, Observer {
            productAdapter.replaceAll(it)
        })
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}