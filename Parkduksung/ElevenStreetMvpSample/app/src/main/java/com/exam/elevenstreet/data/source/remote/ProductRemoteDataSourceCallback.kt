package com.exam.elevenstreet.data.source.remote

import android.util.Log
import com.example.elevenstreet.ProductResponse

interface ProductRemoteDataSourceCallback {


    fun onSuccess(productList: List<ProductResponse>)

    fun onFailure(message: String) {
        Log.d("Error", message)
    }
}

