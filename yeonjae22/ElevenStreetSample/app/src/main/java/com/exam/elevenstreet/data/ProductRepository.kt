package com.exam.elevenstreet.data

import com.example.elevenstreet.ProductResponse

class ProductRepository() {

    interface RepositoryCallBack {
        fun onSuccess(productList: List<ProductResponse>)
        fun onFailure(message: String)
    }

}