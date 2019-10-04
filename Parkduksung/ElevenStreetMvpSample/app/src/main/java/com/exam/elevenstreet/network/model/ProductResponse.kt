package com.example.elevenstreet

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.model.ProductItemCallback
import com.exam.elevenstreet.data.model.ProductItemData
import com.exam.elevenstreet.util.App
import java.io.IOException
import java.net.URL


data class ProductResponse @JvmOverloads constructor(
    var productCode: String = "",
    var productName: String = "",
    var productPrice: String = "",
    var productImage: String = ""
) : ProductItemData {

    override fun toProductItem(callback: ProductItemCallback) {
        Thread(Runnable {

            try {
                val url = URL(productImage)
                val conn = url.openConnection()
                conn.connect()
                val stream = conn.getInputStream()
                val productImageBitmap = BitmapFactory.decodeStream(stream)


                callback.onSuccess(
                    ProductItem(
                        productName,
                        productPrice,
                        productImageBitmap
                    )
                )
            } catch (e: IOException) {
                val notConnectBitmap =
                    ContextCompat.getDrawable(
                        App.instance.context(),
                        R.drawable.not_connect
                    ) as BitmapDrawable

                callback.onSuccess(
                    ProductItem(
                        productName,
                        productPrice,
                        notConnectBitmap.bitmap
                    )
                )
            }
        }).start()
    }

}