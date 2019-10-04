package com.exam.elevenstreet.data.repository

interface ProductRepository {

    fun getSearchByKeyword(keyword: String, callback: ProductRepositoryCallback)

}