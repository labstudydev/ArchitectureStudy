package com.exam.elevenstreet.data.source.remote

import com.example.elevenstreet.ProductResponse

interface ProductRemoteDataSourceCallback {


    fun getProductList(productList: List<ProductResponse>)


}

