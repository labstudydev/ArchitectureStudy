package com.exam.elevenstreet.data.repository

import com.exam.elevenstreet.data.source.local.ProductLocalDataSource

class ProductRepository (
    private val productLocalDataSource: ProductLocalDataSource
){

}