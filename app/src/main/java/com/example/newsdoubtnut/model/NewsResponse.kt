package com.example.newsdoubtnut.model

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("status")
    var statusCode: String,


    @SerializedName("articles")
    var content: List<News>
)