package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.ProductCallBack

interface ProductLocalDataSource {
    fun loadCacheProductData(callback: ProductCallBack)
}