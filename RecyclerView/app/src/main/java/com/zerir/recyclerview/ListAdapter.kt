package com.zerir.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zerir.recyclerview.databinding.RowItemBinding

class ListAdapter(
    private val list: List<ListItem>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = list[position]
                holder.bind(item, position, onItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem, position: Int, onItemClickListener: OnItemClickListener) {
            binding.item = item
            binding.position = position
            binding.listener = onItemClickListener
        }
    }

}

interface OnItemClickListener {
    fun onItemClicked(item: ListItem, position: Int)
}