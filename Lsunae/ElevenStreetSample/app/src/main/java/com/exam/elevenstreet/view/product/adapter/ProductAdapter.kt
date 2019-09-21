package com.exam.elevenstreet.view.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter(private val items: MutableList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)

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
        val code = itemView.tv_product_code
        val name = itemView.tv_product_name
        val price = itemView.tv_product_price
    }

    fun replaceAll(productList: List<ProductResponse>){
        items.clear()
        items.addAll(productList)
        notifyDataSetChanged()
    }

//    fun ClearData(){
//
//        notifyDataSetChanged()
//    }
}