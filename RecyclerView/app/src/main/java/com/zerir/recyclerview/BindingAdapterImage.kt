package com.zerir.recyclerview

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["imageResource"])
fun ImageView.addImageResource(imageResource: Int) {
    this.setImageResource(imageResource)
}