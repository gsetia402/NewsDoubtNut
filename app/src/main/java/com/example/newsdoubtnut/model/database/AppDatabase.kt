package com.example.newsdoubtnut.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsdoubtnut.model.News
import com.example.newsdoubtnut.model.NewsDao

@Database(entities = [News::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}