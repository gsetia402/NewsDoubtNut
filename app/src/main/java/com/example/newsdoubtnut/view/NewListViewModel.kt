package com.example.newsdoubtnut.view

import com.example.newsdoubtnut.repo.NewsActivityRepo
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsdoubtnut.R
import com.example.newsdoubtnut.model.News
import com.example.newsdoubtnut.model.NewsDao
import com.example.newsdoubtnut.network.NewsApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NewsListViewModel(private val newsDao: NewsDao) : ViewModel() {
    @Inject
    lateinit var articleApi: NewsApi
    val articleListAdapter: NewsListAdapter = NewsListAdapter()

    val errorMessage: MutableLiveData<Int?> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadArticles() }

    private lateinit var subscription: Disposable

    private var newsResponseList: MutableLiveData<List<News>>? = null

    init {
        loadArticles()
    }


    private fun loadArticles() {
        newsResponseList = NewsActivityRepo.getServicesApiCall()
        newsResponseList!!.value?.let { onRetrieveArticleListSuccess(it) }
    }

    private fun onRetrieveArticleListSuccess(articleList: List<News>) {
        articleListAdapter.updateArticleList(articleList)
    }

    private fun onRetrieveArticleListError() {
        errorMessage.value = R.string.post_error
    }
}