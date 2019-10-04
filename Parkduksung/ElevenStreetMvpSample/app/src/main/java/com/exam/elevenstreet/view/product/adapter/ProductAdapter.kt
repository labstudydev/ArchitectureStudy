package com.exam.elevenstreet.view.product.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import kotlinx.android.synthetic.main.list_layout.view.*


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(holder.context).inflate(R.layout.list_layout, holder, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem: ProductItem = productList[position]

        holder.run {
            productName.text = productItem.productName
            productPrice.text = productItem.productPrice
            productImage.setImageBitmap(productItem.productImage)

        }

    }

    override fun getItemCount(): Int =
        productList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName: TextView = itemView.product_name_tv
        val productPrice: TextView = itemView.product_price_tv
        val productImage: ImageView = itemView.product_image_tv

    }

    fun addData(productItem: ProductItem) {
        productList.add(productItem)
        notifyItemInserted(productList.lastIndex)
    }

    fun clearListData() {
        productList.clear()
        notifyDataSetChanged()
    }


}
