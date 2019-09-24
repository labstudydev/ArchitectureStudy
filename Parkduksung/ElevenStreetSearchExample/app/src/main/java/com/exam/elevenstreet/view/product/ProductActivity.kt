package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.Repository.ProductRepository
import com.exam.elevenstreet.data.Repository.ProductRepositoryData
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRepositoryData: ProductRepositoryData

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        productRepositoryData =
            ProductRepository.getInstance(ProductRemoteDataSource(), ProductLocalDataSource())
        productAdapter = ProductAdapter()
        startView()

    }

    private fun startView() {

        recyclerview_product.run {


            this.adapter = productAdapter
            productRepositoryData.getProductRepositoryData2 { productlist: List<ProductResponse> ->
                productAdapter.addData(productlist)
            }
            layoutManager = LinearLayoutManager(this@ProductActivity)

        }



        search_button.setOnClickListener {
            productRepositoryData.getProductRepositoryData("${search_text.text}") { productlist: List<ProductResponse> ->
                runOnUiThread {
                    productAdapter.clearListData()
                    productAdapter.addData(productlist)
                }
            }
        }


    }


}


