package com.zerir.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val list: List<ListItem>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position, onItemClickListener)
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val headTv = view.findViewById<TextView>(R.id.head_tv)
        private val descTv = view.findViewById<TextView>(R.id.desc_tv)

        private lateinit var onItemClickListener: OnItemClickListener
        private lateinit var item: ListItem
        private var itemPosition: Int = 0

        init {
            view.setOnClickListener {
                onItemClickListener.onItemClicked(item = item, position = itemPosition)
            }
        }

        fun bind(item: ListItem, position: Int, onItemClickListener: OnItemClickListener) {
            this.item = item
            this.onItemClickListener = onItemClickListener

            this.headTv.text = item.head
            this.descTv.text = item.desc
            this.itemPosition = position
        }
    }

}

interface OnItemClickListener {
    fun onItemClicked(item: ListItem, position: Int)
}