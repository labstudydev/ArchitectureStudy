package com.exam.elevenstreet.view.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.ListItemBinding
import com.example.elevenstreet.ProductResponse

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding
    private lateinit var listener: OnClickListener

    // View에 작업이 일어나면 Listener, CallBack은 데이터만 받아옴
    interface OnClickListener {
        fun onClick(productResponse: ProductResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    private val items = mutableListOf<ProductResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    fun addData(addDataList: List<ProductResponse>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listener!!, items[position])//null 체크
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OnClickListener, item: ProductResponse) {
            binding.run {
                productResponse = item
                itemView.setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}