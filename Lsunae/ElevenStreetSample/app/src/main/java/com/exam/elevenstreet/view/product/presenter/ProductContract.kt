package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.network.model.ProductResponse

interface ProductContract {
    interface View {
        fun showProductList(productList: List<ProductResponse>)
    }

    interface Presenter {
        fun searchByKeyword(keyword:String)
    }
}