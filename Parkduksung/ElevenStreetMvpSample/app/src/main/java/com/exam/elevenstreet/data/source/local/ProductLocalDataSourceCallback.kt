package com.exam.elevenstreet.data.source.local

import android.util.Log
import com.example.elevenstreet.ProductResponse

interface ProductLocalDataSourceCallback {
    fun onSuccess(productList: List<ProductResponse>)

    fun onFailure(message: String) {
        Log.d("Error", message)
    }
}