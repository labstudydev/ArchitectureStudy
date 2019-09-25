package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.ProductActivity
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productView: ProductConstract.View
) : ProductConstract.Presenter {


    override fun startPresenter() {

        ProductRepositoryImpl.getInstance(
            ProductRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance(ProductActivity.CALL_URL)
            ),
            ProductLocalDataSourceImpl.getInstance()
        ).getProductRepositoryLocalData(object : ProductRepository {
            override fun getProductRepositoryRemoteData(
                keyword: String,
                productList: List<ProductResponse>
            ) {

            }

            override fun getProductRepositoryLocalData(productList: List<ProductResponse>) {
                productView.showStartProductList(productList)
            }

        })


    }

    override fun searchByKeyword(keyword: String) {
        ProductRepositoryImpl.getInstance(
            ProductRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance(ProductActivity.CALL_URL)
            ),
            ProductLocalDataSourceImpl.getInstance()
        ).getProductRepositoryRemoteData(keyword, object : ProductRepository {
            override fun getProductRepositoryRemoteData(
                keyword: String,
                productList: List<ProductResponse>
            ) {
                productView.showSearchProductList(keyword, productList)
            }

            override fun getProductRepositoryLocalData(productList: List<ProductResponse>) {

            }

        })


    }


}