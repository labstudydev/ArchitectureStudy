package com.exam.elevenstreet.view.product

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.exam.elevenstreet.R
import kotlinx.android.synthetic.main.fragment_layout.*

class ProductFragment : Fragment(),
    OnBackPressedListener {


    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productImage: ImageView


    override fun onBackPressed(): Boolean {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
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


        return inflater.inflate(R.layout.fragment_layout, container, false).also {


            productName = it.findViewById(R.id.fragment_product_name)
            productPrice = it.findViewById(R.id.fragment_product_price)
            productImage = it.findViewById(R.id.fragment_product_image)


            val bundle = arguments


            productName.text = bundle?.getString("Name")
            productPrice.text = bundle?.getString("Price")
            productImage.setImageBitmap(bundle?.getParcelable("Image"))

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)


        backbutton.setOnClickListener {
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


}