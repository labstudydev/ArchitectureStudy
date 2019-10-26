package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.view.product.base.BasePresenter
import com.exam.elevenstreet.view.product.base.BaseView
import com.example.elevenstreet.ProductResponse

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showProduct(productList: List<ProductResponse>)
        fun setupView()
    }

    interface Presenter : BasePresenter {
        fun searchByKeyword(keyword: String)

    }
}