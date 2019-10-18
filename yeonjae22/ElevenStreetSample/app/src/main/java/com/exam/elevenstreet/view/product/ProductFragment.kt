package com.exam.elevenstreet.view.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exam.elevenstreet.ProductActivity
import com.exam.elevenstreet.R
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        product_name_tv.text = arguments!!.getString("productName")
        product_price_tv.text = arguments!!.getString("productPrice")
        product_seller_tv.text = arguments!!.getString("productSeller")
        product_delivery_tv.text = arguments!!.getString("productDelivery")


        btn_back.setOnClickListener {
            (activity as ProductActivity).back(this)
        }
    }

    fun newInstance(
        productName: String, productPrice: String, productSeller: String,
        productDelivery: String
    ): ProductFragment {
        val frag = ProductFragment()
        val args = Bundle()
        args.putString("productName", productName)
        args.putString("productPrice", productPrice)
        args.putString("productSeller", productSeller)
        args.putString("productDelivery", productDelivery)
        frag.arguments = args
        return frag
    }
}