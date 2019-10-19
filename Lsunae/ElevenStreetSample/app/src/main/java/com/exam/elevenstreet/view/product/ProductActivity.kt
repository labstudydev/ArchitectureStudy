package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view_product
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.databinding.ActivityMainBinding
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.presenter.ProductContract

class ProductActivity : AppCompatActivity() /*ProductContract.View*/ {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    private val productRepository = ProductRepository(
        ProductRemoteDataSource.getInstance
            (RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")),
        ProductLocalDataSource.getInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val activityMainBinding = DataBindingUtil.setContentView(R.layout.activity_main)


        val layoutManager = LinearLayoutManager(this@ProductActivity)
        activityMainBinding.recycler_view_product.layoutManager = layoutManager

        productAdapter = ProductAdapter(mutableListOf<ProductResponse>())

//        val productList = ProductLocalDataSource().getProductDataList(object :ProductCallback{
//            override fun onSuccess(productList: List<ProductResponse>) {
//                productAdapter.replaceAll(productList)
//            }
//
//            override fun onFailure(message: String) {
//                Log.d("tag", message)
//            }
//        })

        setupView()

        activityMainBinding.btn_search.setOnClickListener {
            productRepository.getProductList("${activityMainBinding.edit_search.text}", object : ProductCallback{
                override fun onSuccess(productList: List<ProductResponse>) {
                        productAdapter.replaceAll(productList)
                }

                override fun onFailure(message: String) {
                    Log.d("tag", message)
                }
            })
        }
    }

    private fun setupView() {
//        productRepository.getProductList("빵", object :ProductCallback{
//            override fun onSuccess(productList: List<ProductResponse>) {
//                recycler_view_product.run {
//                    adapter = productAdapter
//                }
//            }
//
//            override fun onFailure(message: String) {
//
//            }
//        })
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }

}