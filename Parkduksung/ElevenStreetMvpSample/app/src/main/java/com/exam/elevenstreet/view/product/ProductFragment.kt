package com.exam.elevenstreet.view.product

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.FragmentLayoutBinding


class ProductFragment : Fragment(),
    OnBackPressedListener {

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productImage: ImageView

    private lateinit var fragmentLayoutBinding: FragmentLayoutBinding


    override fun onBackPressed(): Boolean {

        requireFragmentManager().beginTransaction().remove(this).commit()

        return true
    }


    override fun onAttach(context: Context?) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        fragmentLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false)

        return fragmentLayoutBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments

        productName = fragmentLayoutBinding.fragmentProductName
        productPrice = fragmentLayoutBinding.fragmentProductPrice
        productImage = fragmentLayoutBinding.fragmentProductImage


        productName.text = bundle?.getString("Name")
        productPrice.text = bundle?.getString("Price")
        productImage.setImageBitmap(bundle?.getParcelable("Image"))


        fragmentLayoutBinding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }


    companion object {

        fun newInstance(
            productName: String,
            productPrice: String,
            productImage: Bitmap?
        ) = ProductFragment().apply {
            arguments = Bundle().apply {
                putString("Name", productName)
                putString("Price", productPrice)
                productImage?.let {
                    putParcelable("Image", it)
                }

            }

        }


    }

}