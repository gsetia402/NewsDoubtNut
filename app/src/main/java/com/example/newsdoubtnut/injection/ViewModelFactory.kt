package com.example.newsdoubtnut.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.newsdoubtnut.model.database.AppDatabase
import com.example.newsdoubtnut.view.NewsListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "news").build()
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(db.newsDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}