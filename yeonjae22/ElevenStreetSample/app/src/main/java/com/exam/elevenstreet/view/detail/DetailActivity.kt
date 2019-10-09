package com.exam.elevenstreet.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.detail.presenter.DetailContract
import com.exam.elevenstreet.view.detail.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)

        val detailList = intent.getStringArrayListExtra("detailList")
        presenter.checkProductCode(detailList)

        back.setOnClickListener {
            finish()
        }
    }

    override fun showProductDetail(detailList: ArrayList<String>) {
        product_name_tv.text =  detailList[0]
        product_price_tv.text = detailList[1]
        product_seller_tv.text = detailList[2]
        product_delivery_tv.text = detailList[3]
    }
}