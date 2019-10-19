package com.exam.elevenstreet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exam.elevenstreet.databinding.ListItemBinding
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding
    var listener: OnClickListener? = null

    // View에 작업이 일어나면 Listener, CallBack은 데이터만 받아옴
    interface OnClickListener {
        fun onClick(productResponse: ProductResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    private val items = mutableListOf<ProductResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    fun addData(addDataList: List<ProductResponse>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.run {
            bind(listener!!, item, binding)
            itemView.tag = item
        }
    }

    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OnClickListener, item: ProductResponse, binding: ListItemBinding) {
            binding.run {
                Glide.with(App.instance.context()).load(item.productImage)
                    .override(200, 200)
                    .into(productImg)
                productNameTv.text = item.productName
                productPriceTv.text = item.productPrice
                productCodeTv.text = item.productCode
                cardView.setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}