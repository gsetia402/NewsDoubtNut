package com.example.newsdoubtnut.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface NewsDao {
    @get:Query("SELECT * FROM News")
    val all: List<News>

    @Insert
    fun insert(vararg users: News)

    @Transaction
    @Insert
    fun insertAll(news: List<News>)

}