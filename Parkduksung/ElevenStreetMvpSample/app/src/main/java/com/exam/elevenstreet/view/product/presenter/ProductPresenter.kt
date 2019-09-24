package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.Repository.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productRepositoryData: ProductRepository,
    private val productView: ProductConstract.View
) : ProductConstract.Presenter {


    override fun showStart() {
        productRepositoryData.getProductRepositoryData2 { productList: List<ProductResponse> ->
            productView.showStartProductList(productList)
        }

    }

    override fun searchByKeyword(keyword: String) {

        productRepositoryData.getProductRepositoryData(keyword) { productList: List<ProductResponse> ->
            productView.showSearchProductList(productList)
        }

    }


}