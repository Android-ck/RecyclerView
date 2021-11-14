package com.zerir.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class ListAdapter(
    private val listenOnTitle: OnItemClickListener<ListTypeItem.ListItemTitle>? = null,
    private val listenOnImage: OnItemClickListener<ListTypeItem.ListItemImage>? = null,
    private val listenOnDetails: OnItemClickListener<ListTypeItem.ListItemDetails>? = null,
) : ListAdapter<ListTypeItem, RecyclerView.ViewHolder>(ItemDiffUtils()) {

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is ListTypeItem.ListItemTitle -> R.layout.row_title_item
            is ListTypeItem.ListItemImage -> R.layout.row_image_item
            is ListTypeItem.ListItemDetails -> R.layout.row_details_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.row_title_item -> ViewHolder.TitleViewHolder.from(parent)
            R.layout.row_image_item -> ViewHolder.ImageViewHolder.from(parent)
            R.layout.row_details_item -> ViewHolder.DetailsViewHolder.from(parent)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder.TitleViewHolder -> {
                val item = getItem(position) as ListTypeItem.ListItemTitle
                holder.bind(item, listenOnTitle)
            }
            is ViewHolder.ImageViewHolder -> {
                val item = getItem(position) as ListTypeItem.ListItemImage
                holder.bind(item, listenOnImage)
            }
            is ViewHolder.DetailsViewHolder -> {
                val item = getItem(position) as ListTypeItem.ListItemDetails
                holder.bind(item, listenOnDetails)
            }
        }
    }

}

class ItemDiffUtils : DiffUtil.ItemCallback<ListTypeItem>() {

    override fun areItemsTheSame(oldItem: ListTypeItem, newItem: ListTypeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListTypeItem, newItem: ListTypeItem): Boolean {
        return oldItem == oldItem
    }

}