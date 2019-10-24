package com.exam.elevenstreet.ext

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun ImageView.setImage(bitmap: Bitmap?) {
    this.setImageBitmap(bitmap)
}