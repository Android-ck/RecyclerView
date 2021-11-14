package com.zerir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zerir.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener, SwipeToDoAction {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ListAdapter
    private val list = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list.addAll(loadDataManual())
        adapter = setupAdapter(list, this)
        binding.adapter = adapter
        binding.action = this
    }

    override fun onItemClicked(item: ListItem, position: Int) {
        val message = "Item #$position Clicked!\nH: ${item.head}, D: ${item.desc}"
        Log.d("Item", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun action(position: Int) {
        deleteAndUpdateUi(position)
    }

    private fun deleteAndUpdateUi(position: Int) {
        //delete if only odd number
        if(position % 2 != 0) {
            list.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        // reverse the swipe
        else {
            adapter.notifyItemChanged(position)
        }
    }

    private fun setupAdapter(list: List<ListItem>, onItemClickListener: OnItemClickListener): ListAdapter {
        return ListAdapter(list, onItemClickListener)
    }

    private fun loadDataManual() : List<ListItem> {
        val listItem = ArrayList<ListItem>()
        for (index in 1..14) {
            listItem.add(
                ListItem(
                    head = "Head $index",
                    desc = "Some description of input $index",
                )
            )
        }
        return listItem
    }

}