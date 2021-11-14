package com.zerir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.zerir.recyclerview.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = ListAdapter(
            listenOnTitle = object: OnItemClickListener<ListTypeItem.ListItemTitle> {
                override fun onItemClicked(item: ListTypeItem.ListItemTitle, position: Int) {
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_LONG).show()
                } },
            listenOnImage = object: OnItemClickListener<ListTypeItem.ListItemImage> {
                override fun onItemClicked(item: ListTypeItem.ListItemImage, position: Int) {
                    Toast.makeText(this@MainActivity, "image ${item.imageResource}", Toast.LENGTH_LONG).show()
                } },
            listenOnDetails = object: OnItemClickListener<ListTypeItem.ListItemDetails> {
                override fun onItemClicked(item: ListTypeItem.ListItemDetails, position: Int) {
                    Toast.makeText(this@MainActivity, item.name, Toast.LENGTH_LONG).show()
                } },
        )

        //random dynamic width
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % 4) {
                    0 -> 1
                    1 -> 2
                    3 -> 3
                    4 -> 4
                    else -> 4
                }
            }
        }
        binding.listRv.layoutManager = layoutManager

        binding.adapter = adapter

        adapter.submitList(loadDataManual())
    }

    private fun loadDataManual(): List<ListTypeItem> {
        val list = ArrayList<ListTypeItem>()

        for (index in 1..15) {
            val title = ListTypeItem.ListItemTitle(title = "title $index")
            val images = ArrayList<ListTypeItem.ListItemImage>()
            for(i in 1..index % 5){
                images.add(
                    ListTypeItem.ListItemImage(
                        imageResource = R.drawable.ic_launcher_foreground
                    )
                )
            }
            val details = ListTypeItem.ListItemDetails(
                name = "name $index",
                desc = "some description of $index",
                logoResource = R.drawable.ic_launcher_foreground
            )
            list.add(title)
            list.addAll(images)
            list.add(details)
        }

        return list
    }

}