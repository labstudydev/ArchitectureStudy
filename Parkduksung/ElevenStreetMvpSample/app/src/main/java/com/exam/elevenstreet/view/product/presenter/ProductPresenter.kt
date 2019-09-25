package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.repository.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productRepositoryData: ProductRepository,
    private val productView: ProductConstract.View
) : ProductConstract.Presenter {


    override fun startPresenter() {
        productRepositoryData.getProductRepositoryLocalData { productList: List<ProductResponse> ->
            productView.showStartProductList(productList)
        }
    }

    override fun searchByKeyword(keyword: String) {
        productRepositoryData.getProductRepositoryRemoteData(keyword) { productList: List<ProductResponse> ->
            productView.showSearchProductList(productList)
        }
    }


}