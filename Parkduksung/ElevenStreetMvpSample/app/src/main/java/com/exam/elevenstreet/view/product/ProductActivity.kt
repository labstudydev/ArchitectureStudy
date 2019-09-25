package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.exam.elevenstreet.view.product.presenter.ProductConstract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity(), ProductConstract.View {


    private lateinit var presenter: ProductConstract.Presenter
    private val productAdapter by lazy { ProductAdapter() }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProductPresenter(
            ProductRepositoryImpl.getInstance(
                ProductRemoteDataSourceImpl.getInstance(RetrofitInstance.getInstance(CALL_URL)),
                ProductLocalDataSourceImpl.getInstance()
            ), this
        )
        startView()

    }

    private fun startView() {
        recyclerview_product.run {
            this.adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity)
            presenter.startPresenter()
        }

        search_button.setOnClickListener {
            presenter.searchByKeyword("${search_text.text}")
        }

    }

    override fun showStartProductList(productList: List<ProductResponse>) {
        runOnUiThread {
            productAdapter.addData(productList)
        }
    }

    override fun showSearchProductList(productList: List<ProductResponse>) {
        runOnUiThread {
            productAdapter.clearListData()
            productAdapter.addData(productList)
        }
    }


    companion object {
        private const val CALL_URL = "https://openapi.11st.co.kr/openapi/"
    }

}


