package com.exam.elevenstreet.data.source.local

interface ProductLocalDataSource {
    fun getLocalData(
        callback: ProductLocalDataSourceCallback
    )
}