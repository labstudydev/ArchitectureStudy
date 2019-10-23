package com.example.elevenstreet

import android.util.Log
import com.exam.elevenstreet.data.CallBack
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class ProductXmlPullParserHandler {
    private val products = ArrayList<ProductResponse>()
    private var product: ProductResponse? = null
    private var text: String? = null


    fun parse(inputStream: InputStream, callback: CallBack): ArrayList<ProductResponse> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagName.equals("Product", ignoreCase = true)) {
                        // create a new instance of product
                        Log.d("TEST", "START_TAG")
                        product = ProductResponse()
                    }
                    XmlPullParser.TEXT -> {
                        text = parser.text
                    }
                    XmlPullParser.END_TAG -> if (tagName.equals("Product", ignoreCase = true)) {
                        // add product object to list
                        Log.d("TEST", "END_TAG")
                        product?.let { products.add(it) }
                    } else if (tagName.equals("ProductName", ignoreCase = true)) {
                        product?.productName = text.orEmpty()
                    } else if (tagName.equals("ProductCode", ignoreCase = true)) {
                        product?.productCode = text.orEmpty()
                    } else if (tagName.equals("ProductPrice", ignoreCase = true)) {
                        product?.productPrice = text.orEmpty()
                    } else if (tagName.equals("ProductImage300", ignoreCase = true)) {
                        product?.productImage = '"'+text.orEmpty()+'"'
                    }
                    else -> {

                    }
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return products
    }
}