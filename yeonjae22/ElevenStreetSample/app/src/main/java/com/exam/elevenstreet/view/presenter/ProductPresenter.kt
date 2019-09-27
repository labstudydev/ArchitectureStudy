package com.exam.elevenstreet.view.presenter

import com.exam.elevenstreet.data.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductPresenter(private val productRepository: ProductRepository,
                       private val productView: ProductContract.View
): ProductContract.Presenter {
    private lateinit var searchKeyword: String

    override fun searchByKeyword(keyword: String){
        searchKeyword = keyword
        getProductList(searchKeyword)
    }
    private fun getProductList(keyword: String) {
        productRepository.getSearchByKeyword(keyword, object :ProductRepository.CallBack{
            override fun onSuccess(productList: List<ProductResponse>) {
                productView.showProductList(productList)
            }

            override fun onFailure(message: String) {
            }

        })
    }
}