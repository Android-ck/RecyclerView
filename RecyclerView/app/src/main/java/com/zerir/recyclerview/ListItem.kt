package com.zerir.recyclerview

import java.util.*

data class ListItem(
    val id: String = UUID.randomUUID().toString(),
    var head: String,
    val desc: String,
)
