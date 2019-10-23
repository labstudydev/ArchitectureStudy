package com.exam.elevenstreet.view.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productItemList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int =
        productItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productItem: ProductItem = productItemList[position]
        holder.run {
            productName.text = productItem.productName
            productPrice.text = productItem.productPrice
            productImage.setImageBitmap(productItem.productImage)
        }
    }


    fun addData(productItem: ProductItem) {
        productItemList.add(productItem)
        notifyItemInserted(productItemList.lastIndex)
    }

    fun clearListData() {
        productItemList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

        val productName: TextView = itemProductBinding.productNameTv
        val productPrice: TextView = itemProductBinding.productPriceTv
        val productImage: ImageView = itemProductBinding.productImageIv


    }


}