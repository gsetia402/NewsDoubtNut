package com.example.newsdoubtnut.network

import com.example.newsdoubtnut.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?sources=techcrunch&apiKey=0df41d01eda94412a993f1a73c2d8c42")
    fun getNews(): Call<NewsResponse>
}