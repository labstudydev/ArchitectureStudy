package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view_product
import java.net.URL
import android.annotation.SuppressLint
import android.os.AsyncTask
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import retrofit2.http.HTTP
import java.io.InputStream


class ProductActivity : AppCompatActivity() {

    private var elevenStreetApi: ElevenStreetApi? = null
    private lateinit var productAdapter: ProductAdapter
    private lateinit var keyWord: String
    private var pageNum = 1
    private var productTask = ProductTask()

    //val productRepository by lazy {ProductRepository()}

//    private lateinit var productTask: ProductRemoteDataSource.ProductTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.exam.elevenstreet.R.layout.activity_main)

        val productList = dataBinding()


        productAdapter = ProductAdapter(productList.toMutableList())

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        setupView()

        btn_search.setOnClickListener {
            keyWord = "${edit_search.text}"
            pageNum = 1
//            productAdapter.ClearData()
//            ProductRemoteDataSource.ProductTask.getSearchByKeyword()
//            var productTask = ProductRemoteDataSource().ProductTask()

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

        RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")

        val call = elevenStreetApi?.getProductList(
            context.getString(com.exam.elevenstreet.R.string.eleven_street_API_KEY),
            ProductActivity.API_CODE,
            keyWord,
            pageNum
        )

        val url: String? = "${call?.request()?.url()}"
        val targetURL = URL(url)
        val inputStream = targetURL.openStream()

        return ProductXmlPullParserHandler().parse(inputStream)

    }

    inner class ProductTask : AsyncTask<String, Void, List<ProductResponse>>() {

        override fun doInBackground(vararg p0: String): List<ProductResponse> {

            return getSearchByKeyword(keyWord, pageNum)

        }

//        //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
//        override fun onPreExecute() {
//
//        }
//
//        override fun onProgressUpdate(vararg values: String?) {
////            super.onProgressUpdate(*values)
//        }
//
//        override fun onPostExecute(result: List<ProductResponse>?) {
////            super.onPostExecute(result)
//        }
    }


    private fun dataBinding(): List<ProductResponse> {
        val inputStream = assets.open("ElevenStreetOpenApiService.xml")
        return ProductXmlPullParserHandler().parse(inputStream)
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }

}