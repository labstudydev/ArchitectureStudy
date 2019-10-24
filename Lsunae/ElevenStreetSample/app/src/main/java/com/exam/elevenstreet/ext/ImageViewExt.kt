package com.exam.elevenstreet.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageLoad")
fun ImageView.setImage(imageUrl: String) {
//    Glide.with(context).load(imageUrl).override
//        .override(200, 200)
//        .into(this)
}