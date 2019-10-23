package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.CallBack
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.example.elevenstreet.ProductResponse

interface ProductRepositoryInterface
{
    fun getProductItem(
        callback : CallBack
    )
}