package com.zerir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zerir.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val list = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list.addAll(loadDataManual())
        binding.adapter = setupAdapter(list, this)
    }

    override fun onItemClicked(item: ListItem, position: Int) {
        val message = "Item #$position Clicked!\nH: ${item.head}, D: ${item.desc}"
        Log.d("Item", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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