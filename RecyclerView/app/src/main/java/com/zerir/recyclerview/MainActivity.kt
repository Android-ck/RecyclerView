package com.zerir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zerir.recyclerview.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnItemClickListener, SwipeToDoAction {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = setupAdapter(this)

        binding.adapter = adapter
        binding.action = this

        adapter.submitList(loadDataManual())
    }

    override fun onItemClicked(position: Int) {
        val item = adapter.currentList[position]
        val message = "Item #$position ${item.id} Clicked!\nH: ${item.head}, D: ${item.desc}"
        Log.d("Item", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // add new item
        if(position % 2 == 0) addAndUpdateUi()
        // update header
        else updateAndUpdateUi(position)
    }

    override fun action(position: Int) {
        deleteAndUpdateUi(position)
    }

    private fun updateAndUpdateUi(position: Int) {
        val oldItem = adapter.currentList[position]
        val newItem = oldItem.copy(head = "new head")
        val list = ArrayList(adapter.currentList)
        list.remove(oldItem)
        list.add(position, newItem)
        adapter.submitList(list)
    }

    private fun addAndUpdateUi() {
        val index = adapter.itemCount + 1
        val item = ListItem(
            head = "Head $index",
            desc = "Some description of input $index",
        )
        val list = ArrayList(adapter.currentList)
        list.add(item)
        adapter.submitList(list)
    }

    private fun deleteAndUpdateUi(position: Int) {
        //delete if only odd number
        if(position % 2 != 0) {
            val list = ArrayList(adapter.currentList)
            list.removeAt(position)
            adapter.submitList(list)
        }
        // reverse the swipe
        else {
            adapter.notifyItemChanged(position)
        }
    }

    private fun setupAdapter(onItemClickListener: OnItemClickListener): ListAdapter {
        return ListAdapter(onItemClickListener)
    }

    private fun loadDataManual() : List<ListItem> {
        val listItem = ArrayList<ListItem>()
        for (index in 1..3) {
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