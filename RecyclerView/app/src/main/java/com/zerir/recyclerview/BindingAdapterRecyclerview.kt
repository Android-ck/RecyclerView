package com.zerir.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["adapter", "isFixedSize", "swipeToDoAction"])
fun RecyclerView.setupAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?,
    isFixedSize: Boolean? = null,
    swipeToDoAction: SwipeToDoAction? = null,
) {
    this.setHasFixedSize(isFixedSize ?: true)
    this.adapter = adapter
    swipeToDoAction?.let {
        val itemTouch = generateItemTouch(swipeToDoAction)
        val itemTouchHelper = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(this)
    }
}

interface SwipeToDoAction {
    fun action(position: Int)
}

private fun generateItemTouch(swipeToDoAction: SwipeToDoAction): ItemTouchHelper.SimpleCallback {
    return object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    val position = viewHolder.absoluteAdapterPosition
                    swipeToDoAction.action(position)
                }
            }
        }
    }
}