package com.exam.elevenstreet.view.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.BR
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productItemList = mutableListOf<ProductItem>()
    private lateinit var binding: ItemProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        productItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productItem: ProductItem = productItemList[position]
        holder.run {

            bind(productItem)


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

    class ViewHolder(private val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bind(item: ProductItem) {
            itemProductBinding.setVariable(BR.productItem, item)
        }


    }


}