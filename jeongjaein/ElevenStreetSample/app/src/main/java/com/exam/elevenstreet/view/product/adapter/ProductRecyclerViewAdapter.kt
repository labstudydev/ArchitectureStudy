package com.exam.elevenstreet.view.product.adapter

import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.view.product.adapter.Viewholder.ProductViewHolder
import com.example.elevenstreet.ProductResponse

class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductViewHolder>() {


    private var productItemList = mutableListOf<ProductResponse>()

    interface OnItemClickListener {
        fun onClick()
    }

    private var clickListener: OnItemClickListener? = null

    fun replaceAll() {
        this.productItemList.clear()
        this.productItemList.addAll(productItemList)
        notifyDataSetChanged()
    }

    fun addData(productList: List<ProductResponse>) {
        productItemList.addAll(productList)
        notifyDataSetChanged()


    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(productItemList[position])


    override fun getItemCount(): Int =
        productItemList.size

//    var isRunning = true
//    var thread = ThreadClass()
//    thread.start()
//
//    override fun onDestroy(){
//        super.onDestroy()
//        isRunning = false
//    }

//    inner class ThreadClass : Thread() {
//        override fun run() {
//            while (isRunning) {
//                SystemClock.sleep(100)
//                var time = System.currentTimeMillis()
//                Log.d("test1", "쓰래드" : ${ time }"")
//            }
//
//        }
//    }
}



