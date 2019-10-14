package com.exam.elevenstreet.data.source.remote

interface ProductRemoteDataSource {

    fun getRemoteData(
        keyword: String,
        callback: ProductRemoteDataSourceCallback
    )

}