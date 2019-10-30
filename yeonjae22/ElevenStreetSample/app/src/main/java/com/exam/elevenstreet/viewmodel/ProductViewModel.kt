package com.exam.elevenstreet.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.elevenstreet.data.ProductCallBack
import com.exam.elevenstreet.data.repository.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    val productItemList = MutableLiveData<List<ProductResponse>>()

    fun searchByKeyword(keyword: String) {
        productRepository.getSearchByKeyword(keyword, object : ProductCallBack {
            override fun onSuccess(productList: List<ProductResponse>) {
                productItemList.postValue(productList)
            }

            override fun onFailure(message: String) {
            }
        })
    }
}