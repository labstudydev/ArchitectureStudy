package com.exam.elevenstreet.data.source.local

import com.exam.elevenstreet.data.CallBack

interface ProductLocalDataSourceInterface {
    fun getProductList(callback: CallBack)
}