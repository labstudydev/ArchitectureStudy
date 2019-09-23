package com.exam.elevenstreet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.exam.elevenstreet.data.ProductRemoteDataSource
import com.exam.elevenstreet.data.ProductRepository
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val productRepository = ProductRepository(
            ProductRemoteDataSource.getInstance(),
            ProductLocalDataSource.getInstance()
        )

        val manager = LinearLayoutManager(this)
        recycler_view.layoutManager = manager
        recycler_view.setHasFixedSize(true)

            productRepository.getSearchByKeyword("수건", object : ProductRepository.CallBack {
                override fun onSuccess(productList: List<ProductResponse>) {
                    recycler_view.adapter = adapter
                    adapter.addData(productList)
                }

                override fun onFailure(message: String) {
                }
            })

        btn_search.setOnClickListener {
                productRepository.getSearchByKeyword("${edt_search.text}", object :
                    ProductRepository.CallBack {
                    override fun onSuccess(productList: List<ProductResponse>) {
                        adapter.addData(productList)
                    }

                    override fun onFailure(message: String) {
                        Log.d("tag", message)
                    }
                })
        }
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}