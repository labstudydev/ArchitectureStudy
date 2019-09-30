package com.exam.elevenstreet.view.product.presenter

interface ProductContract {
    interface View {
        fun showProductList(productList: List<ProductPresenter>)
    }

    interface Presenter {
        fun searchByKeyword(keyword:String)
    }
}