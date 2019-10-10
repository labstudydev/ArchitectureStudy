package com.exam.elevenstreet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.elevenstreet.ProductResponse

class ProductRecyclerViewAdapter(RecyclerList: List<ProductResponse>?) ://mutablelist는 약간 추상적인 배열?느낌 ProductResponse에 정의되어있는 대로 배열을말하는둣
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    private var productList: List<ProductResponse>? = RecyclerList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val ProductResponse = productList!![position]
        holder!!.textName.text = ProductResponse.productName
        holder!!.textPrice.text = ProductResponse.productPrice
        holder!!.textCode.text = ProductResponse.productCode
        holder!!.textImage.text = ProductResponse.productImage


    }


    override fun getItemCount(): Int {
        return productList!!.size
    }


    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textName = view.findViewById(R.id.product_name_tv) as TextView
        var textPrice = view.findViewById(R.id.product_price_tv) as TextView
        var textCode = view.findViewById(R.id.product_code_tv) as TextView
        var textImage = view.findViewById(R.id.product_code_tv) as TextView

    }
}
