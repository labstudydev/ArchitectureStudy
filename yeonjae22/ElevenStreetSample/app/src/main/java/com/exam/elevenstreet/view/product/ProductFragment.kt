package com.exam.elevenstreet.view.product

import android.os.Bundle
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.FragmentProductBinding
import com.exam.elevenstreet.view.base.BaseFragment

class ProductFragment : BaseFragment<FragmentProductBinding>(R.layout.fragment_product) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.run {
            productNameTv.text = arguments!!.getString("productName")
            productPriceTv.text = arguments!!.getString("productPrice")
            productSellerTv.text = arguments!!.getString("productSeller")
            productDeliveryTv.text = arguments!!.getString("productDelivery")
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        view?.setOnClickListener {
            true
        }
    }

    companion object {
        fun newInstance(
            productName: String, productPrice: String, productSeller: String,
            productDelivery: String
        ) = ProductFragment().apply {
            arguments = Bundle().apply {
                putString("productName", productName)
                putString("productPrice", productPrice)
                putString("productSeller", productSeller)
                putString("productDelivery", productDelivery)
            }
        }
    }
}