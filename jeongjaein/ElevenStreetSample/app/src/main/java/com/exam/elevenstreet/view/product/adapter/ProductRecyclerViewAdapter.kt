package com.exam.elevenstreet.view.product.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.view.product.adapter.Viewholder.ProductViewHolder
import com.example.elevenstreet.ProductResponse

class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private var productList = mutableListOf<ProductResponse>()

    interface OnItemClickListener {
        fun onClick()
    }

    private var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(productList[position])


    override fun getItemCount(): Int =
        productList.size
}



