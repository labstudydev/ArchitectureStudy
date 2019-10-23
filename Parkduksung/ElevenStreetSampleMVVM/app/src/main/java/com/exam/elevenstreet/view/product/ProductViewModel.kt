package com.exam.elevenstreet.view.product

import android.util.Log
import androidx.databinding.ObservableField
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl


class ProductViewModel(private val productRepository: ProductRepository) {


    private var isProductLastPrevious: Boolean = false
    private lateinit var lastSearchKeyword: String
    private var pageNum = 1
    private var totalCount = 0

    val productItemList = ObservableField<List<ProductItem>>()

    private var _productItemList = mutableListOf<ProductItem>()


    fun searchByKeyword(keyword: String) {
        pageNum = 1
        totalCount = 0
        lastSearchKeyword = keyword
        isProductLastPrevious = false
        getProductList(lastSearchKeyword, pageNum)
    }

    fun loadNextProduct(itemCount: Int) {
        if (itemCount >= (pageNum * 50)) {
            getProductList(lastSearchKeyword, ++pageNum)
        } else if (isProductLastPrevious) {
            Log.d("Error", "검색 결과가 없습니다.")
        }
    }

    fun checkProductEnd(itemCount: Int) {
        if ((totalCount - itemCount) < 50) {
            isProductLastPrevious = true
        }
    }

    private fun getProductList(keyword: String, pageNum: Int) {
        productRepository.getSearchByKeyword(keyword, pageNum) { productResponse, totalCount ->

            if (totalCount == ProductRepositoryImpl.EXCEPTION) {
                Log.d("Error", "검색 결과가 없습니다.")
            } else {
                this.totalCount = totalCount

                if (productResponse.isEmpty()) {
                    Log.d("Error", "검색 결과가 없습니다.")
                } else {
                    // 데이터 로드 시작 progress show

//                    prductView.showLoadingProgress()
////                    productView.showLoadingProgress()


//                    productResponse.forEach {
//                        it.toProductItem { productItem ->
//                            _productItemList.add(
//                                productItem
//                            )
//                        }
//                    }

//
//                    // 데이터변환 ProductResponse -> ProductItem
                    productResponse.forEach {
                        it.toProductItem { productItem ->
                            _productItemList.add(productItem)
                        }
                    }

                    productItemList.set(_productItemList)
//
//                    //데이터 셋팅 완료. progress hide
//                    prductView.endDataLoad()
                }
            }
        }
    }
}