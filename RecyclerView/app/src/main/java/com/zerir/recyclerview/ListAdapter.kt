package com.zerir.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zerir.recyclerview.databinding.RowItemBinding

class ListAdapter(
    private val onItemClickListener: OnItemClickListener,
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(ItemDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position)
                holder.bind(item, onItemClickListener)
            }
        }
    }

    class ViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var onItemClickListener: OnItemClickListener

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                onItemClickListener.onItemClicked(position = position)
            }
        }

        fun bind(item: ListItem, onItemClickListener: OnItemClickListener) {
            binding.item = item
            this.onItemClickListener = onItemClickListener
        }
    }

}

class ItemDiffUtils : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.head == newItem.head &&
                oldItem.desc == newItem.desc
    }

}

interface OnItemClickListener {
    fun onItemClicked(position: Int)
}