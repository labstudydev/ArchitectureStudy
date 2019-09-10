package com.exam.elevenstreet.view.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_item.view.*

//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.TextView
//import com.exam.elevenstreet.R
//import com.example.elevenstreet.ProductResponse


class ProductAdapter(val items: List<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    //var items: List<ProductResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)

    } //= ViewHolder(parent)

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
}


//class ListAdapter(val context: Context, val productList: List<ProductResponse>) : BaseAdapter() {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View = LayoutInflater.from(convertView.context).inflate(R.layout.activity_main, null)
//
//        val name = view.findViewById<TextView>(R.id.product_name_tv)
//        val price = view.findViewById<TextView>(R.id.product_price_tv)
//        val code = view.findViewById<TextView>(R.id.product_code_tv)
//
//        val product = productList[position]
//
//        name.text = product.productName
//        price.text = product.productPrice
//        code.text = product.productCode
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {  // =
//        return productList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getCount(): Int {
//        return productList.size
//    }
//
//}
