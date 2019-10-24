package com.exam.elevenstreet.viewmodel

import androidx.databinding.ObservableField
import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.data.repository.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductViewModel(private val productRepository: ProductRepository) {
    val productItemList = ObservableField<List<ProductResponse>>()

    fun searchByKeyword(keyword: String) {
        productRepository.getSearchByKeyword(keyword, object : ProductCallBack {
            override fun onSuccess(productList: List<ProductResponse>) {
                productItemList.set(productList)
            }

            override fun onFailure(message: String) {
            }
        })
    }
}