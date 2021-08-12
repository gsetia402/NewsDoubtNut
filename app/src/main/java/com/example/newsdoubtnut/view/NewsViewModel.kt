package com.example.newsdoubtnut.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsdoubtnut.model.News
import com.example.newsdoubtnut.util.getFormatedDate

class NewsViewModel : ViewModel() {
    private val title = MutableLiveData<String>()
    private val ingress = MutableLiveData<String>()
    private val image = MutableLiveData<String>()
    private val date = MutableLiveData<String>()


    fun bind(news: News) {
        title.value = news.title
//        ingress.value = news.ingress
        image.value = news.urlToImage
//        date.value = news.dateTime
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getIngress(): MutableLiveData<String> {
        return ingress
    }

    fun getImage(): MutableLiveData<String> {
        return image
    }

    fun getDate(): MutableLiveData<String> {

        val modifiedDate = getFormatedDate(date.value.toString())
        date.value = modifiedDate;

        return date
    }
}