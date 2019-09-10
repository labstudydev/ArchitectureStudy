package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.ElevenStreetApi
import com.exam.elevenstreet.R
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view_product
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item.*
import java.net.URL


class MainActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productList = dataBinding()

        productAdapter = ProductAdapter(productList)

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        setupView()

    }

    private fun setupView() {
        recycler_view_product.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = productAdapter
        }
    }

    private fun getSerchByKeyword(keyWord: String, pageNum: Int) {
        val call = elevenStreetApi?.getProductList(
            getString(R.string.eleven_street_API_KEY),
            API_CODE,
            "수건",
            1
        )

        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()

    }

    private fun dataBinding(): List<ProductResponse> {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        return ProductXmlPullParserHandler().parse(inputStream)
    }

    companion object {
        private const val TAG = "MainActivity"
        const val API_CODE = "ProductSearch"
    }

}
