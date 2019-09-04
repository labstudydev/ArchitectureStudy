package com.exam.elevenstreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter(val items: List<ProductResponse>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ProductAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listener: View.OnClickListener, item: ProductResponse) {
            Glide.with(view.context).load(item.productImage)
                .override(200, 200)
                .into(view.product_img)
            view.product_name_tv.text = item.productName
            view.product_price_tv.text = item.productPrice
            view.product_code_tv.text = item.productCode
            view.setOnClickListener(listener)
        }
    }
}