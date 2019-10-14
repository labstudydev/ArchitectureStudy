package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ActivityMainBinding
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter


class ProductActivity : AppCompatActivity(), ProductContract.View {


    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.container_your_topic)

        if ((fragment as? OnBackPressedListener)?.onBackPressed() != null) {

            Log.d("뒤로가기", "Fragment")

        } else {
            Log.d("뒤로가기", "ProductActivity")

            super.onBackPressed()
        }


    }

    private lateinit var presenter: ProductContract.Presenter
    private val productAdapter by lazy { ProductAdapter() }
    private lateinit var activityMainBinding: ActivityMainBinding


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        presenter = ProductPresenter(
            this
        )
        startView()
        itemClick()

    }


    private fun startView() {


        activityMainBinding.recyclerviewProduct.run {
            this.adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity)


        }

        activityMainBinding.searchButton.setOnClickListener {

            productAdapter.clearListData()
            presenter.searchByKeyword("${activityMainBinding.searchText.text}")


        }


    }


    private fun itemClick() {

        productAdapter.itemClick = object : ProductAdapter.ItemClick {
            override fun onClick(view: View, productItem: ProductItem) {

                val productFragment = ProductFragment.newInstance(
                    productItem.productName,
                    productItem.productPrice,
                    productItem.productImage
                )

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_your_topic, productFragment).commit()

            }

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


