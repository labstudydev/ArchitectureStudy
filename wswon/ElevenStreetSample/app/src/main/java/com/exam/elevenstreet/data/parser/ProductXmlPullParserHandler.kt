package com.exam.elevenstreet.data.parser

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.network.model.ProductResponse
import java.io.IOException
import java.io.InputStream

class ProductXmlPullParserHandler {
    private val products = ArrayList<ProductResponse>()
    private var product: ProductResponse? = null
    private var text: String? = null


    fun parse(inputStream: InputStream, callback: (productList: List<ProductResponse>, totalCount: Int) -> Unit) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType

            var totalCount = 0

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagName.equals("Product", ignoreCase = true)) {
                        // create a new instance of product
                        product = ProductResponse()
                    }
                    XmlPullParser.TEXT -> {
                        text = parser.text
                    }
                    XmlPullParser.END_TAG -> {
                        when {
                            tagName.equals("Product", ignoreCase = true) -> {
                                product?.let { products.add(it) }
                            }
                            tagName.equals("ProductName", ignoreCase = true) -> {
                                product?.productName = text.orEmpty()
                            }
                            tagName.equals("ProductCode", ignoreCase = true) -> {
                                product?.productCode = text.orEmpty()
                            }
                            tagName.equals("ProductPrice", ignoreCase = true) -> {
                                product?.productPrice = text.orEmpty()
                            }
                            tagName.equals("ProductImage300", ignoreCase = true) -> {
                                product?.productImage = text.orEmpty()
                            }
                            tagName.equals("Delivery", ignoreCase = true) -> {
                                product?.delivery = text.orEmpty()
                            }
                            tagName.equals("TotalCount", ignoreCase = true) -> {
                                totalCount = text.orEmpty().toInt()
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
            callback(products, totalCount)

        } catch (e: XmlPullParserException) {
            callback(products, ProductRepositoryImpl.EXCEPTION)
        } catch (e: IOException) {
            callback(products, ProductRepositoryImpl.EXCEPTION)
        }
    }
}