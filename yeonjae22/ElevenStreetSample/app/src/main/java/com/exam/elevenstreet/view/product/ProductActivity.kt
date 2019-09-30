package com.exam.elevenstreet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ElevenStreetApi
import com.exam.elevenstreet.data.ProductLocalDataSource
import com.exam.elevenstreet.data.ProductRemoteDataSource
import com.exam.elevenstreet.data.ProductRepository
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.presenter.ProductContract
import com.exam.elevenstreet.view.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {
    override lateinit var presenter: ProductContract.Presenter
    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        presenter = ProductPresenter(
            ProductRepository.getInstance(
                ProductRemoteDataSource.getInstance
                    (RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")),
                ProductLocalDataSource.getInstance()
            ),
            this
        )
        setupView()

        btn_search.setOnClickListener {
            presenter.searchByKeyword("${edt_search.text}")
        }
    }

    override fun showProductList(productList: List<ProductResponse>) {
        adapter.addData(productList)
    }

    private fun setupView() {
        val manager = LinearLayoutManager(this)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}