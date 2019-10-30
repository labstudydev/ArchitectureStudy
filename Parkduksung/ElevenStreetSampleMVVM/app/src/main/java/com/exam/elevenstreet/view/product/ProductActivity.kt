package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityProductBinding
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_product.*


class ProductActivity : AppCompatActivity() {

    private val productAdapter by lazy { ProductAdapter() }

    private val productViewModel by lazy {
        ProductViewModel(
            ProductRepositoryImpl.getInstance(
                ProductRemoteDataSourceImpl.getInstance(
                    RetrofitInstance.getInstance(CALL_URL)
                ),
                ProductLocalDataSourceImpl.getInstance()
            )
        )
    }

    private lateinit var activityProductBinding: ActivityProductBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        setupView()
    }

    fun showProductList(item: ProductItem) {
        runOnUiThread {
            productAdapter.addData(item)
            activityProductBinding.tvProductCount.text = "${productAdapter.itemCount} 개"
        }
    }

    private fun setupView() {
        activityProductBinding.rvProduct.run {
            layoutManager = LinearLayoutManager(this@ProductActivity)
            adapter = productAdapter


            productViewModel.productItem.observe(this@ProductActivity, Observer {
                showProductList(it)
            })




            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1)) {
                        productViewModel.loadNextProduct(productAdapter.itemCount)
                    }
                }
            })


        }

        btn_search.setOnClickListener {
            productAdapter.clearListData()
            productViewModel.searchByKeyword("${activityProductBinding.edtInputKeyword.text}")
        }
    }


    companion object {
        private const val CALL_URL = "https://openapi.11st.co.kr/openapi/"
        private const val TAG = "ProductActivity"
    }
}


