package com.exam.elevenstreet.view.product.presenter

import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.data.CallBack
import com.exam.elevenstreet.data.repository.ProductRepository
import com.example.elevenstreet.ProductResponse

class ProductPresenter(
    private val productRepository: ProductRepository.Companion,
    private val productView: MainContract.View

) : AppCompatActivity(), MainContract.Presenter {

    override fun start() {

    }

    override fun databind() {

        productRepository.getInstance(object : CallBack {

            override fun onSuccess(productList: List<ProductResponse>) {
                productView?.showProduct(productList)

            }

            override fun onFailure(message: String) {

            }


        })


    }
    /*      val productAdapter = ProductRecyclerViewAdapter(productList)
          val globalContext = MyApplication
          val inputStream = globalContext.context().assets.open("ElevenStreetOpenApiService.xml")

          productList = ProductXmlPullParserHandler().parse(inputStream)

  */
}
