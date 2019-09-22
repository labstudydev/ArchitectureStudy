package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.view.base.BasePresenter
import com.exam.elevenstreet.view.base.BaseView

interface ProductContract {
    interface View: BaseView<Presenter>{
        fun showProductList(item: ProductItem)
        fun showMessage(message: String)
        fun showLoadingProgress()
        fun endDataLoad()
    }

    interface Presenter: BasePresenter{
        fun searchByKeyword(keyword: String)
        fun loadNextProduct(itemCount: Int)
        fun checkProductEnd(itemCount: Int)
    }
}