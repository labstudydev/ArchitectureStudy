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
            ProductRepository.getInstance(
                ProductRemoteDataSource(),
                ProductLocalDataSource()
            ), this
        )
        startView()

    }

    private fun startView() {
        recyclerview_product.run {
            this.adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity)
            presenter.showStart()
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

}


