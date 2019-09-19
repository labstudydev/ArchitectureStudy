package com.exam.elevenstreet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.exam.elevenstreet.data.ProductRemoteDataSource
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
            val productList1 =
                productRemoteDataSource.ProductTask().execute("${edt_search.text}").get()
            adapter.addData(productList1)
        }
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}