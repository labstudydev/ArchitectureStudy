package com.exam.elevenstreet.view.detail.presenter

interface DetailContract {

    interface View {
        var presenter: Presenter
        fun showProductDetail(detailList: ArrayList<String>)
    }

    interface Presenter {
        fun checkProductCode(detailList: ArrayList<String>)
    }
}