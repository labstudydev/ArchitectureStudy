package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productRepository: ProductRepositoryImpl,
    private val productView: ProductContract.View
) : ProductContract.Presenter {

    override fun searchByKeyword(keyword: String) {
        productRepository.getSearchByKeyword(keyword, object : ProductCallBack {
            override fun onSuccess(productList: List<ProductResponse>) {
                productView.showProductList(productList)
            }

            override fun onFailure(message: String) {
            }
        })
    }
}