package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.network.model.ProductResponse

class ProductPresenter(
    private val productView: ProductContract.View,
    private var productRepositoryImpl: ProductRepositoryImpl
) : ProductContract.Presenter {

    override fun searchByKeyword(keyword: String) {
        productRepositoryImpl.getProductList(keyword, object : ProductCallback {
            override fun onFailure(message: String) {

            }

            override fun onSuccess(productList: List<ProductResponse>) {
                productView.showProductList(productList)
            }
        })
    }

}