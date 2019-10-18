package com.exam.elevenstreet.view.product.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.exam.elevenstreet.R
import com.exam.elevenstreet.view.product.MainActivity
import com.example.elevenstreet.ProductResponse
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import kotlinx.android.synthetic.main.item_product.view.*
import java.net.URL

import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.module.GlideApp
import com.exam.elevenstreet.view.product.adapter.Viewholder.ProductViewHolder
import kotlin.text.replace

class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private var productList = mutableListOf<ProductResponse>()

    interface OnItemClickListener {
        fun onClick()
    }

    private var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(productList[position])


    override fun getItemCount(): Int =
        productList.size

}
//    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        private val textName: TextView = itemView.product_name_tv
//        private val textPrice: TextView = itemView.product_price_tv
//        private val textCode: TextView = itemView.product_code_tv
//        private val textImage: ImageView = itemView.product_image_iv

//        fun bind(productResponse: ProductResponse) {
//            val listener = View.OnClickListener { clickListener?.onClick() }
//
//
//
//            itemView.run {
//
//                val tempurl = productResponse.productImage.replace("\n".toRegex(), "")
//
//                val replaceurl = tempurl.replace(" ".toRegex(), "")
//
//                GlideApp.with(itemView)
//                    .load("http://i.011st.com/t/300/pd/18/2/4/2/2/4/8/eqSaS/2138242248_B.jpg")
//                    .error(R.drawable.ic_alarm_on_black_24dp)
//                    .into(textImage)
//
//                itemView.setOnClickListener(listener)
//                textName.text = productResponse.productName
//                textPrice.text = productResponse.productPrice
//                textCode.text = replaceurl
////                var count = 0
////                val array : List<Int> = ArrayList<Int>()
////                for(i in 0..replaceurl.length){
////                    if(replaceurl[i] == productResponse.productImage[i]){
////                        count ++
////
////                    }
////                }
//                val compareurl ="http://i.011st.com/t/300/pd/18/2/4/2/2/4/8/eqSaS/2138242248_B.jpg"
//                if(replaceurl.length == compareurl.length){
//                    textCode.text = "고영찬 바보@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
//                }


//            }


/*            Picasso.get()
                .load(productResponse.productImage)
                .error(R.drawable.ic_alarm_on_black_24dp)
                .into(textImage)*/

//            Glide.with(itemView)
//                .load(url)
//                .into(textImage)
//            var url = productResponse.productImage
//            textImage.setImageBitmap(productResponse.productImage)





