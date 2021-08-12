package com.example.newsdoubtnut.util

import com.example.newsdoubtnut.network.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import com.example.newsdoubtnut.BuildConfig
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitClient {

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
    }

    val apiInterface: NewsApi by lazy {
        retrofitClient
            .build()
            .create(NewsApi::class.java)
    }
}