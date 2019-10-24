package com.exam.elevenstreet.data

import com.example.elevenstreet.ProductResponse

interface CallBack {
    fun onFailure(message: String)

    fun onSuccess(productList: List<ProductResponse>)
}