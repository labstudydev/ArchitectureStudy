package com.exam.elevenstreet.view.product.presenter

import android.util.Log
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.model.ProductItemCallback
import com.exam.elevenstreet.data.repository.ProductRepositoryCallback
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.activity.ProductActivity
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productView: ProductContract.View
) : ProductContract.Presenter {


    override fun searchByKeyword(keyword: String) {
        ProductRepositoryImpl.getInstance(
            ProductRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance(ProductActivity.CALL_URL)
            ),
            ProductLocalDataSourceImpl.getInstance()
        ).getSearchByKeyword(keyword, object : ProductRepositoryCallback {

            override fun onSuccess(productList: List<ProductResponse>) {

                productList.map {
                    it.toProductItem(object : ProductItemCallback {
                        override fun onSuccess(productItem: ProductItem) {

                            productView.showProductList(productItem)
                        }

                    })
                }

            }

            override fun onFailure(message: String) {
                Log.d("Error", message)
            }
        })


    }


}
