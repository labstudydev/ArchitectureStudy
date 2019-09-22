package com.exam.elevenstreet.view.product.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_product.view.*
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem

class ProductViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
) {
    fun bind(productItem: ProductItem){
        super.itemView.run {
            product_name_tv.text = productItem.productName
            product_price_tv.text = productItem.productPrice
            product_image_iv.setImageBitmap(productItem.productImage)
        }
    }
}