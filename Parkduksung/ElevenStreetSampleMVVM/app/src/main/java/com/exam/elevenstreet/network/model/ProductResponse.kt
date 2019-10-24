package com.exam.elevenstreet.network.model

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.exam.elevenstreet.App
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import java.io.IOException
import java.net.URL
import java.text.DecimalFormat

data class ProductResponse @JvmOverloads constructor(
    var productCode: String = "",
    var productName: String = "",
    var productPrice: String = "",
    var productImage: String = ""
) {
    fun toProductItem(callback: (productItem: ProductItem) -> Unit) {


        Thread(Runnable {


            val priceFormat = DecimalFormat("###,###원")

            try {
                val url = URL(productImage)
                val conn = url.openConnection()
                conn.connect()
                val stream = conn.getInputStream()
                val productImageBitmap = BitmapFactory.decodeStream(stream)

                callback(
                    ProductItem(
                        productName,
                        priceFormat.format(productPrice.toInt()),
                        productImageBitmap
                    )
                )
            } catch (e: IOException) {
                val notConnectBitmap =
                    ContextCompat.getDrawable(
                        App.instance.context(),
                        R.drawable.not_connect
                    ) as BitmapDrawable
                callback(
                    ProductItem(
                        productName,
                        priceFormat.format(productPrice.toInt()),
                        notConnectBitmap.bitmap
                    )
                )
            }
        }).start()
    }
}