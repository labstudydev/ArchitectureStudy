package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.view.product.base.BasePresenter
import com.exam.elevenstreet.view.product.base.BaseView
import com.example.elevenstreet.ProductResponse

interface MainContract {
    interface View:BaseView<Presenter>{
    }
interface Presenter : BasePresenter{
    fun databind(): List<ProductResponse>
    fun setupView()
}
}