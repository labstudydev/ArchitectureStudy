package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity(), ProductContract.View {


    private lateinit var presenter: ProductContract.Presenter
    private val productAdapter by lazy { ProductAdapter() }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProductPresenter(
            this
        )
        startView()

    }

    private fun startView() {
        recyclerview_product.run {
            this.adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity)

        }

        search_button.setOnClickListener {
            productAdapter.clearListData()
            presenter.searchByKeyword("${search_text.text}")
        }

    }


    override fun showProductList(item: ProductItem) {
        runOnUiThread {
            productAdapter.addData(item)
        }
    }


    companion object {
        const val CALL_URL = "https://openapi.11st.co.kr/openapi/"
    }

}


