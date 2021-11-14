package com.zerir.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["adapter", "isFixedSize"])
fun RecyclerView.setupAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?,
    isFixedSize: Boolean?
) {
    this.setHasFixedSize(isFixedSize ?: true)
    this.adapter = adapter
}