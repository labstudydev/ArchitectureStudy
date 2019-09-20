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

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ProductRepository().ConnectNetwork()) {
            search_button.setOnClickListener {

                var inputkeyword = "${search_text.text}"
                ProductRemoteDataSource()
                    .getProductlist(inputkeyword) { productList ->
                        runOnUiThread {
                            recyclerview_product.run {
                                var adapter =
                                    ProductAdapter(productList as ArrayList<ProductResponse>)
                                this.adapter = adapter
                                layoutManager = LinearLayoutManager(this@ProductActivity)
                            }
                        }

                    }

            }

        } else {
            ProductLocalDataSource()
                .getProductlist("ElevenStreetOpenApiService.xml") { productList ->

                    recyclerview_product.run {
                        var adapter =
                            ProductAdapter(productList as ArrayList<ProductResponse>)
                        recyclerview_product.adapter = adapter
                        layoutManager = LinearLayoutManager(this@ProductActivity)

                    }

                }
        }


    }


}
