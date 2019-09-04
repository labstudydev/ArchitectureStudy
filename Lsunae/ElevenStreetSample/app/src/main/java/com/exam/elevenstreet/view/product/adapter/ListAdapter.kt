package com.exam.elevenstreet.view.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse

class ListAdapter(val context: Context, val ProductList: List<ProductResponse>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_main, null)
        val Name = view.findViewById<TextView>(R.id.product_name_tv)
        val Price = view.findViewById<TextView>(R.id.product_price_tv)
        val Code = view.findViewById<TextView>(R.id.product_code_tv)

        val product = ProductList[position]

        Name.text = product.productName
        Price.text = product.productPrice
        Code.text = product.productCode

        return view
    }

    override fun getItem(position: Int): Any {
        return ProductList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return ProductList.size
    }

}
