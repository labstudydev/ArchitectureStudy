package com.exam.elevenstreet.view.product.adapter.Viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.module.GlideApp
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
) {
    private val textName: TextView = itemView.product_name_tv
    private val textPrice: TextView = itemView.product_price_tv
    private val textCode: TextView = itemView.product_code_tv
    private val textImage: ImageView = itemView.product_image_iv

    fun bind(productResponse: ProductResponse) {

        val temp1url = productResponse.productImage.replace("\n", "")

        val temp2url = temp1url.replace(" ", "")

        val replaceurl = temp2url.replace("\"", "")
        GlideApp.with(itemView)
            .load(replaceurl)
            .error(R.drawable.ic_alarm_on_black_24dp)
            .into(textImage)

        textName.text = productResponse.productName
        textPrice.text = productResponse.productPrice
        textCode.text = productResponse.productCode


    }
}