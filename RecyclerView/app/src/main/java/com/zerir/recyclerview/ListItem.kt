package com.zerir.recyclerview

import java.util.*

sealed class ListTypeItem(val id: String) {

    data class ListItemTitle(
        val title: String,
    ): ListTypeItem(UUID.randomUUID().toString())

    data class ListItemImage(
        val imageResource: Int,
    ): ListTypeItem(UUID.randomUUID().toString())

    data class ListItemDetails(
        val name: String,
        val desc: String,
        val logoResource: Int
    ): ListTypeItem(UUID.randomUUID().toString())
}
