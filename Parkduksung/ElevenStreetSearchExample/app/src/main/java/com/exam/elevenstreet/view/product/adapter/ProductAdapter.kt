package com.exam.elevenstreet.view.product.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_layout.view.*


class ProductAdapter(val productList: ArrayList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductResponse = productList[position]

        holder.productName.text = product.productName
        holder.productPrice.text = product.productPrice
        holder.productCode.text = product.productCode
        holder.productImage.text = product.productImage


    }

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(holder.context).inflate(R.layout.list_layout, holder, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 100
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName = itemView.product_name_tv
        val productPrice = itemView.product_price_tv
        val productCode = itemView.product_code_tv
        val productImage = itemView.product_image_tv


    }


}
