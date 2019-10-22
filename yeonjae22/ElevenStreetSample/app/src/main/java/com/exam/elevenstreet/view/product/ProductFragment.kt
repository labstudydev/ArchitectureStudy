package com.exam.elevenstreet.view.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.productNameTv.text = arguments!!.getString("productName")
        binding.productPriceTv.text = arguments!!.getString("productPrice")
        binding.productSellerTv.text = arguments!!.getString("productSeller")
        binding.productDeliveryTv.text = arguments!!.getString("productDelivery")

        binding.btnBack.setOnClickListener {
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