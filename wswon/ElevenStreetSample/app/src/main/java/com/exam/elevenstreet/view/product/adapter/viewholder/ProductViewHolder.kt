package com.exam.elevenstreet.view.product.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ItemProductBinding
import com.exam.elevenstreet.view.product.adapter.ProductAdapter

class ProductViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
) {
     private val binding: ItemProductBinding? = DataBindingUtil.bind(itemView)

    fun bind(productItem: ProductItem, listener: ProductAdapter.OnClickListener?){
        super.itemView.run {
            setOnClickListener {
                listener?.onClick(productItem)
            }
            binding?.run {
                productNameTv.text = productItem.productName
                productPriceTv.text = productItem.productPrice
                productImageIv.setImageBitmap(productItem.productImage)
            }

        }
    }
}