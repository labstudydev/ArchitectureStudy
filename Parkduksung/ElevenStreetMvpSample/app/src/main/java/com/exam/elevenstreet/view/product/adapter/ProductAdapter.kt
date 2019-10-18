package com.exam.elevenstreet.view.product.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.databinding.ListLayoutBinding


class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, productItem: ProductItem)
    }

    var itemClick: ItemClick? = null

    private var productList = mutableListOf<ProductItem>()


    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder =

        ViewHolder(ListLayoutBinding.inflate(LayoutInflater.from(holder.context), holder, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem: ProductItem = productList[position]

        holder.itemView.setOnClickListener {

            itemClick?.onClick(it, productItem)

        }


        holder.run {
            productName.text = productItem.productName
            productPrice.text = productItem.productPrice
            productImage.setImageBitmap(productItem.productImage)
        }


    }

    override fun getItemCount(): Int =
        productList.size


    class ViewHolder(listLayoutBinding: ListLayoutBinding) :
        RecyclerView.ViewHolder(listLayoutBinding.root) {

        val productName: TextView = listLayoutBinding.productNameTv
        val productPrice: TextView = listLayoutBinding.productPriceTv
        val productImage: ImageView = listLayoutBinding.productImageIv


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
