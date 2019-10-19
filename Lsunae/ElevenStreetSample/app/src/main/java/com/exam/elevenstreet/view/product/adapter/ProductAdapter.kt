package com.exam.elevenstreet.view.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ListItemBinding
import com.exam.elevenstreet.network.model.ProductResponse
//import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter(private val items: MutableList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var onClickListener:OnClickListener?=null
    private lateinit var binding: ListItemBinding

    interface OnClickListener{
        fun onClick(productItem: ProductItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

       binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)

//        return ViewHolder(view)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                code.text = item.productCode
                name.text = item.productName
                price.text = item.productPrice
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listener: OnClickListener{
            view.run{
                binding.run {
//                    productResponse = item

                }
            }
        })

        val code = itemView.tv_product_code
        val name = itemView.tv_product_name
        val price = itemView.tv_product_price
    }

    fun replaceAll(productList: List<ProductResponse>){
        items.clear()
        items.addAll(productList)
        notifyDataSetChanged()
    }

}