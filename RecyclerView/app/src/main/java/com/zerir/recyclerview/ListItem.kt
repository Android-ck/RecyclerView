package com.zerir.recyclerview

import java.util.*

data class ListItem(
    val id: String = UUID.randomUUID().toString(),
    var head: String,
    val desc: String,
) {

    fun copyWithSameId(head: String = this.head, desc: String = this.desc): ListItem = ListItem(
        id = this.id,
        head = head,
        desc = desc
    )

}
