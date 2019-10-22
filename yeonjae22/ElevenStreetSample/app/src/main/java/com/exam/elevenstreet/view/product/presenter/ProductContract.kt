package com.exam.elevenstreet.view.product.presenter

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