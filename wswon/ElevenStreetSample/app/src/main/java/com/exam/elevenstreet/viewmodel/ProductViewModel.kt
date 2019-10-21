package com.exam.elevenstreet.viewmodel

import androidx.databinding.ObservableField
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl

class ProductViewModel(private val productRepository: ProductRepository) {

    val exceptionMessage = ObservableField<String>()
    val productItem = ObservableField<ProductItem>()
    val showProgressFlag = ObservableField<Boolean>()

    private var isProductLastPrevious: Boolean = false
    private lateinit var lastSearchKeyword: String
    private var pageNum = 1
    private var totalCount = 0

    fun searchByKeyword(keyword: String) {
        pageNum = 1
        totalCount = 0
        lastSearchKeyword = keyword
        isProductLastPrevious = false
        getProductList(keyword, pageNum)
    }

    fun loadNextProduct(itemCount: Int) {
        if (itemCount >= (pageNum * 50)) {
            getProductList(lastSearchKeyword, ++pageNum)
        } else if (isProductLastPrevious) {
            exceptionMessage.set("더이상 데이터가 없습니다")
            exceptionMessage.notifyChange()
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
                exceptionMessage.set("데이터를 로드하지 못했습니다.")
                exceptionMessage.notifyChange()
            } else {
                this.totalCount = totalCount

                if (productResponse.isEmpty()) {
                    exceptionMessage.set("검색 결과가 없습니다.")
                    exceptionMessage.notifyChange()
                } else {
                    // 데이터 로드 시작 progress show
                    showProgressFlag.set(true)
                    // 데이터변환 ProductResponse -> ProductItem
                    productResponse.forEach {
                        it.toProductItem { productItem ->
                            this.productItem.set(productItem)
                        }
                    }
                    //데이터 셋팅 완료. progress hide
                    showProgressFlag.set(false)
                }
            }
        }
    }

}