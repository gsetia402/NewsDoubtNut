package com.example.newsdoubtnut.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsdoubtnut.model.News
import com.example.newsdoubtnut.model.NewsResponse
import com.example.newsdoubtnut.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NewsActivityRepo {


    val newsListReponse = MutableLiveData<List<News>>()

    fun getServicesApiCall(): MutableLiveData<List<News>> {

        val call = RetrofitClient.apiInterface.getNews()

        call.enqueue(object: Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val msg = data!!.statusCode

                newsListReponse.value = data.content
            }
        })

        return newsListReponse

    }
}