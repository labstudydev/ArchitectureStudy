package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.repository.ProductRepositoryInterface

class ProductPresenter(
    private val productRepository: ProductRepositoryInterface,
    private val productView: MainContract.View
) : MainContract.Presenter {

    private var pageNum = 1
    private lateinit var lastSearchKeyword: String

    override fun searchByKeyword(keyword: String) {
        pageNum = 1
        lastSearchKeyword = keyword
        databind(lastSearchKeyword, pageNum)
    }

    override fun start() {

    }

    private fun databind(keyword: String, pageNum: Int) {
        productRepository.getProductItem(keyword, pageNum) { productList ->
            for(productResponse in productList)
            productView.showProduct(productList)
        }


    }

}