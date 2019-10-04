package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.model.ProductItem

interface ProductContract {
    interface View {

        fun showProductList(item: ProductItem)

    }

    interface Presenter {
        fun searchByKeyword(keyword: String)

    }

}

