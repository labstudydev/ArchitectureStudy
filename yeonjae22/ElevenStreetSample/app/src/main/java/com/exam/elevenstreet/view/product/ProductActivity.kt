package com.exam.elevenstreet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.exam.elevenstreet.data.ProductRemoteDataSource
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val productRemoteDataSource = ProductRemoteDataSource()

        val manager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(manager)
        recycler_view.setHasFixedSize(true)

        val productList = ProductLocalDataSource().getProductList()
        recycler_view.adapter = adapter
        adapter.addData(productList)

        btn_search.setOnClickListener {
            val productList =
                productRemoteDataSource.getSearchByKeyword("${edt_search.text}", object :
                    ProductRemoteDataSource.CallBack {
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