package com.exam.elevenstreet.view.presenter

import com.exam.elevenstreet.model.ProductItem
import com.example.elevenstreet.ProductResponse

interface ProductContract {

    interface View {
        var presenter: Presenter
        fun showProductList(productList: List<ProductResponse>)
    }

    interface Presenter {
        fun searchByKeyword(keyword: String)
    }
}