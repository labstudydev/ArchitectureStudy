package com.exam.elevenstreet.view.product.presenter

import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.data.repository.ProductRepositoryInterface

class ProductPresenter : AppCompatActivity(), MainContract.Presenter {
//    private lateinit var productList: List<ProductResponse>
    private val productRepository : ProductRepositoryInterface? = null

    override fun databind(){
        if (productRepository != null) {
            productRepository.getProductItem {
                productRepository.getProductItem {}

            }
        }

  /*      val productAdapter = ProductRecyclerViewAdapter(productList)
        val globalContext = MyApplication
        val inputStream = globalContext.context().assets.open("ElevenStreetOpenApiService.xml")

        productList = ProductXmlPullParserHandler().parse(inputStream)

*/
    }


    override fun start() {


    }

}