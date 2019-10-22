package com.exam.elevenstreet.data

import com.example.elevenstreet.ProductResponse

interface ProductCallBack{
    fun onSuccess(productList: List<ProductResponse>)
    fun onFailure(message: String)
}