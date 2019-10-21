package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.example.elevenstreet.ProductResponse

class ProductRepository :ProductRepositoryInterface{
    override fun getProductItem(callback: (productList: ProductResponse) -> Unit) {
        ProductLocalDataSource.getInstance().getProductList { callback : List<ProductResponse> ->Unit }

    }


}