package com.exam.elevenstreet.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//확장함수
@BindingAdapter("imageLoad")
fun ImageView.setImage(imageUrl: String){
    Glide.with(context).load(imageUrl)
        .override(200, 200)
        .into(this)
}