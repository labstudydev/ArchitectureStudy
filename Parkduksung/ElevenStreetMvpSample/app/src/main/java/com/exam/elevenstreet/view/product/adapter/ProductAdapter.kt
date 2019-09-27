package com.exam.elevenstreet.view.product.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_layout.view.*


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productList = mutableListOf<ProductResponse>()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(holder.context).inflate(R.layout.list_layout, holder, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductResponse = productList[position]

        holder.run {
            productName.text = product.productName
            productPrice.text = product.productPrice
            productCode.text = product.productCode
            productImage.text = product.productImage
        }

    }

    override fun getItemCount(): Int =
        productList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName = itemView.product_name_tv
        val productPrice = itemView.product_price_tv
        val productCode = itemView.product_code_tv
        val productImage = itemView.product_image_tv

    }

    fun addData(product: List<ProductResponse>) =
        productList.addAll(product)

    fun clearListData() {
        productList.clear()
        notifyDataSetChanged()
    }


}
