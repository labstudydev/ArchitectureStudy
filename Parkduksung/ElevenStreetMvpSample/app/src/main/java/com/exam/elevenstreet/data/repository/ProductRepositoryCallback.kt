package com.exam.elevenstreet.data.repository

import android.util.Log
import com.example.elevenstreet.ProductResponse

interface ProductRepositoryCallback {

    fun onSuccess(productList: List<ProductResponse>)


    fun onFailure(message: String) {
        Log.d("Error", message)
    }

}


