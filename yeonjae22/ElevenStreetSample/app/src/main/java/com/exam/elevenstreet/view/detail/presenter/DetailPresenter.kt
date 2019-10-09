package com.exam.elevenstreet.view.detail.presenter

import com.exam.elevenstreet.data.repository.ProductRepositoryImpl

class DetailPresenter(
    private val detailView: DetailContract.View
) : DetailContract.Presenter {

    override fun checkProductCode(detailList: ArrayList<String>) {
        detailView.showProductDetail(detailList)
    }
}