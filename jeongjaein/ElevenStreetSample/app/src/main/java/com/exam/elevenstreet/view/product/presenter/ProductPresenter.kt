package com.exam.elevenstreet.view.product.presenter

import android.util.Log
import com.exam.elevenstreet.data.repository.ProductRepositoryInterface
import kotlin.system.exitProcess

class ProductPresenter(
    private val productRepository: ProductRepositoryInterface,
    private val productView: MainContract.View
) : MainContract.Presenter {


    private var pageNum = 1
    private lateinit var lastSearchKeyword: String
    private var count = 0
    private var count2 = 0

    override fun searchByKeyword(keyword: String) {
        pageNum = 1
        count = 0
        lastSearchKeyword = keyword
        databind(lastSearchKeyword, pageNum)
    }

    override fun nextProduct(count: Int) {
        if(count >=(pageNum *50)){
            databind(lastSearchKeyword,++pageNum)
        }
    }

    override fun start() {

    }

    override fun checkProductEnd(itemCount: Int){
        if((count - itemCount) < 50){

        }
    }

    private fun databind(keyword: String, pageNum: Int) {
        productRepository.getProductItem(keyword, pageNum) { productList ->


            for (productResponse in productList) {
                count++
                productView.showProduct(productList)
                if(count== 2){
                    break
                }
                Log.d("countggick", count.toString())
            }
            count = 1
        }


    }

}