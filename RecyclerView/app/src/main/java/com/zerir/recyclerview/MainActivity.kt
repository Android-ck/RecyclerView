package com.zerir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var listRv: RecyclerView

    private val list = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.addAll(loadDataManual())

        listRv = findViewById(R.id.list_rv)
        listRv.setHasFixedSize(true)
        listRv.layoutManager = LinearLayoutManager(this)
        listRv.adapter = setupAdapter(list)

    }

    private fun setupAdapter(list: List<ListItem>): ListAdapter {
        return ListAdapter(list)
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