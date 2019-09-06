package com.exam.elevenstreet.data

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.io.InputStream


class ProductDataBinding(inputStream: InputStream , recyclerView: RecyclerView){

    val productList = ProductXmlPullParserHandler().parse(inputStream)

    val recyclerViewRun = recyclerView.run {

        var adapter = ProductAdapter(productList as ArrayList<ProductResponse>)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(recyclerView.context)

    }
    fun start(){
        return recyclerViewRun
    }

    init {
        start()
    }


}
