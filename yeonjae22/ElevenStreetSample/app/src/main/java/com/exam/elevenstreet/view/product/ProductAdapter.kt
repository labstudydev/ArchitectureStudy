package com.exam.elevenstreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    val items = mutableListOf<ProductResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ProductAdapter.ViewHolder(view)
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
        val listener = View.OnClickListener { it ->
        }
        holder.run {
            bind(listener, item)
            itemView.tag = item
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listener: View.OnClickListener, item: ProductResponse) {
            view.run {
                Glide.with(context).load(item.productImage)
                    .override(200, 200)
                    .into(product_img)
                product_name_tv.text = item.productName
                product_price_tv.text = item.productPrice
                product_code_tv.text = item.productCode
                setOnClickListener(listener)
            }
        }
    }
}