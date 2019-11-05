package com.exam.elevenstreet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.elevenstreet.data.ProductCallback
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.network.model.ProductResponse

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val productItemList = MutableLiveData<List<ProductResponse>>()

    fun searchByKeyword(keyword: String) {
        productRepository.getProductList(keyword, object : ProductCallback {
            override fun onFailure(message: String) {

            }

            override fun onSuccess(productList: List<ProductResponse>) {
                productItemList.value = productList
            }
        })
    }
}