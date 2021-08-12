package com.example.newsdoubtnut.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val content: String
)
