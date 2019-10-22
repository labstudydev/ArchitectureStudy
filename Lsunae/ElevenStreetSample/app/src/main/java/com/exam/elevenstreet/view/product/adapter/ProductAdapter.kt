package com.exam.elevenstreet.view.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.ListItemBinding
import com.exam.elevenstreet.network.model.ProductResponse

class ProductAdapter(private val items: MutableList<ProductResponse>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var onClickListener: OnClickListener
    private lateinit var binding: ListItemBinding

    interface OnClickListener {
        fun onClick(productItem: ProductResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )
        if (::onClickListener.isLateinit) {

        }
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(onClickListener, items[position])

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listener: OnClickListener, productItem: ProductResponse) {
            if (::onClickListener.isLateinit) {
                listener.onClick(productItem)
            }
            binding.productItem = productItem
        }
    }

    fun replaceAll(productList: List<ProductResponse>) {
        items.clear()
        items.addAll(productList)
        notifyDataSetChanged()
    }

}