package com.exam.elevenstreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exam.elevenstreet.databinding.ListItemBinding
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    //val binding: ListItemBinding? = null
    var listener:OnClickListener? = null

    // View에 작업이 일어나면 Listener, CallBack은 데이터만 받아옴
    interface OnClickListener {
        fun onClick(productResponse: ProductResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    private val items = mutableListOf<ProductResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {

        val binding: ListItemBinding? = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )

        return ViewHolder(binding!!.root)
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
            bind(listener!!, item)
            itemView.tag = item
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listener: OnClickListener, item: ProductResponse) {
            view.run {
                Glide.with(context).load(item.productImage)
                    .override(200, 200)
                    .into(product_img)
                product_name_tv.text = item.productName
                product_price_tv.text = item.productPrice
                product_code_tv.text = item.productCode
                view.setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}