package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.Repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        productAdapter = ProductAdapter()

        if (ProductRepository().ConnectNetwork()) {
            search_button.setOnClickListener {

                ProductRemoteDataSource().getProductlist("${search_text.text}",
                    object : ProductRemoteDataSource.CallBack {
                        override fun onSuccess(productList: List<ProductResponse>) {
                            runOnUiThread {
                                recyclerview_product.run {
                                    this.adapter = productAdapter
                                    productAdapter.clearListData()
                                    productAdapter.addData(productList)
                                    layoutManager = LinearLayoutManager(this@ProductActivity)
                                }
                            }
                        }

                        override fun onFailure(message: String) {

                        }
                    })
            }
        } else {
            val productList = ProductLocalDataSource()
                .getProductlist("ElevenStreetOpenApiService.xml")

            recyclerview_product.run {
                this.adapter = productAdapter
                productAdapter.clearListData()
                productAdapter.addData(productList)
                layoutManager = LinearLayoutManager(this@ProductActivity)

            }
        }
    }
}


