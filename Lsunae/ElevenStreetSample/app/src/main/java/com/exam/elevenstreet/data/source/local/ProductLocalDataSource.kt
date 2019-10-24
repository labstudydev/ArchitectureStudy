package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.ProductCallback

interface ProductLocalDataSource {
    fun getProductDataList(callback: ProductCallback)
}