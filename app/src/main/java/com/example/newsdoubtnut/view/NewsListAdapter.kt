package com.example.newsdoubtnut.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdoubtnut.R
import com.example.newsdoubtnut.databinding.ItemNewsBinding
import com.example.newsdoubtnut.model.News

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private lateinit var articleList: List<News>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return if (::articleList.isInitialized) articleList.size else 0
    }

    fun updateArticleList(postList: List<News>) {
        this.articleList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NewsViewModel()

        fun bind(article: News) {
            viewModel.bind(article)
            binding.viewModel = viewModel
        }
    }
}