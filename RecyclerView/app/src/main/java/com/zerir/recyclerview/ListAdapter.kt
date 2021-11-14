package com.zerir.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val list: List<ListItem>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val head = list[position].head
        val desc = list[position].desc
        holder.bind(head, desc)
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val headTv = view.findViewById<TextView>(R.id.head_tv)
        private val descTv = view.findViewById<TextView>(R.id.desc_tv)

        fun bind(head: String, desc: String) {
            headTv.text = head
            descTv.text = desc
        }

    }

}