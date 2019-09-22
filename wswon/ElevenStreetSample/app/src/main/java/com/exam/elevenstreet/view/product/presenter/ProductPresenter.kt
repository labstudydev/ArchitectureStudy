package com.exam.elevenstreet.view.product.presenter

import com.exam.elevenstreet.data.repository.ProductRepository
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl

class ProductPresenter(
    private val productRepository: ProductRepository,
    private val productView: ProductContract.View
) : ProductContract.Presenter {

    private var isProductLastPrevious: Boolean = false
    private lateinit var lastSearchKeyword: String
    private var pageNum = 1
    private var totalCount = 0

    override fun start() {

    }

    override fun searchByKeyword(keyword: String) {
        pageNum = 1
        totalCount = 0
        lastSearchKeyword = keyword
        isProductLastPrevious = false
        getProductList(lastSearchKeyword, pageNum)
    }

    override fun loadNextProduct(itemCount: Int) {
        if (itemCount >= (pageNum * 50)) {
            getProductList(lastSearchKeyword, ++pageNum)
        } else if (isProductLastPrevious) {
            productView.showMessage("더이상 데이터가 없습니다")
        }
    }

    override fun checkProductEnd(itemCount: Int) {
        if ((totalCount - itemCount) < 50) {
            isProductLastPrevious = true
        }
    }

    private fun getProductList(keyword: String, pageNum: Int) {
        productRepository.getSearchByKeyword(keyword, pageNum) { productResponse, totalCount ->

            if (totalCount == ProductRepositoryImpl.EXCEPTION) {
                productView.showMessage("데이터를 로드하지 못했습니다.")
            } else {
                this.totalCount = totalCount

                if (productResponse.isEmpty()) {
                    productView.showMessage("검색 결과가 없습니다.")
                } else {
                    // 데이터 로드 시작 progress show
                    productView.showLoadingProgress()

                    // 데이터변환 ProductResponse -> ProductItem
                    productResponse.map {
                        it.toProductItem { productItem ->
                            productView.showProductList(productItem)
                        }
                    }

                    //데이터 셋팅 완료. progress hide
                    productView.endDataLoad()
                }
            }
        }
    }


}