package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view_product
import java.net.URL
import android.os.AsyncTask
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.parser.ProductXmlPullParserHandler
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.network.model.ProductResponse



class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null
    private lateinit var productAdapter: ProductAdapter
    private lateinit var keyWord: String
    private var pageNum = 1

    private val productRepository by lazy { ProductRepository(ProductRemoteDataSource(), ProductLocalDataSource()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.exam.elevenstreet.R.layout.activity_main)

        val productList = ProductLocalDataSource().productData()


        productAdapter = ProductAdapter(productList.toMutableList())

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        setupView()

        btn_search.setOnClickListener {

            productRepository.getProductList()

            keyWord = "${edit_search.text}"
            pageNum = 1

            val productList = ProductTask().execute(keyWord, pageNum.toString()).get()
            productAdapter.replaceAll(productList)
        }
    }

    private fun setupView() {
        recycler_view_product.run {
            layoutManager = LinearLayoutManager(this@ProductActivity)
            adapter = productAdapter
        }
    }

    fun getSearchByKeyword(keyWord: String, pageNum: Int): List<ProductResponse> {

        val context = App.instance.context()

        val call = elevenStreetApi?.getProductList(
            context.getString(R.string.eleven_street_API_KEY),
            API_CODE,
            keyWord,
            pageNum
        )

        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()

        return ProductXmlPullParserHandler().parse(inputStream)

    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg params: String): List<ProductResponse> =

            getSearchByKeyword(keyWord, pageNum)

    }

//    private fun dataBinding(): List<ProductResponse> {
//        val inputStream = context.assets.open("ElevenStreetOpenApiService.xml")
//        return ProductXmlPullParserHandler().parse(inputStream)
//    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }

}