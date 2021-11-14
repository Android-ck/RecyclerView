package com.zerir.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.zerir.recyclerview.databinding.RowDetailsItemBinding
import com.zerir.recyclerview.databinding.RowImageItemBinding
import com.zerir.recyclerview.databinding.RowTitleItemBinding

sealed class ViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: RowTitleItemBinding) : ViewHolder(binding) {

        companion object {
            fun from(parent: ViewGroup): TitleViewHolder {
                val context = parent.context
                val layoutInflater = LayoutInflater.from(context)
                val binding = RowTitleItemBinding.inflate(layoutInflater, parent, false)
                return TitleViewHolder(binding)
            }
        }

        private lateinit var item: ListTypeItem.ListItemTitle
        private var onItemClickListener: OnItemClickListener<ListTypeItem.ListItemTitle>? = null

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClicked(item, absoluteAdapterPosition)
            }
        }

        fun bind(item: ListTypeItem.ListItemTitle, onItemClickListener: OnItemClickListener<ListTypeItem.ListItemTitle>?) {
            this.onItemClickListener = onItemClickListener
            this.item = item

            binding.item = item
        }

    }

    class ImageViewHolder(private val binding: RowImageItemBinding) : ViewHolder(binding) {

        companion object {
            fun from(parent: ViewGroup): ImageViewHolder {
                val context = parent.context
                val layoutInflater = LayoutInflater.from(context)
                val binding = RowImageItemBinding.inflate(layoutInflater, parent, false)
                return ImageViewHolder(binding)
            }
        }

        private lateinit var item: ListTypeItem.ListItemImage
        private var onItemClickListener: OnItemClickListener<ListTypeItem.ListItemImage>? = null

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClicked(item, absoluteAdapterPosition)
            }
        }

        fun bind(item: ListTypeItem.ListItemImage, onItemClickListener: OnItemClickListener<ListTypeItem.ListItemImage>?) {
            this.onItemClickListener = onItemClickListener
            this.item = item

            binding.item = item
        }

    }

    class DetailsViewHolder(private val binding: RowDetailsItemBinding) : ViewHolder(binding) {

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val context = parent.context
                val layoutInflater = LayoutInflater.from(context)
                val binding = RowDetailsItemBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }

        private lateinit var item: ListTypeItem.ListItemDetails
        private var onItemClickListener: OnItemClickListener<ListTypeItem.ListItemDetails>? = null

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClicked(item, absoluteAdapterPosition)
            }
        }

        fun bind(item: ListTypeItem.ListItemDetails, onItemClickListener: OnItemClickListener<ListTypeItem.ListItemDetails>?) {
            this.onItemClickListener = onItemClickListener
            this.item = item

            binding.item = item
        }

    }

}

interface OnItemClickListener<T: ListTypeItem> {
    fun onItemClicked(item: T, position: Int)
}