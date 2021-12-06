package com.shawky.newsapp.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.shawky.newsapp.R
import com.shawky.newsapp.constants.routs.GlideApp
import com.shawky.newsapp.models.NewsModel
import com.shawky.newsapp.databinding.NewsItemLayoutBinding
import com.shawky.newsapp.utils.Utils
import kotlin.collections.ArrayList

class NewsRvAdapter(private val newsList : ArrayList<NewsModel> ,val onItemClicked : (news : NewsModel) -> Unit) : RecyclerView.Adapter<NewsRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val layoutBinding: NewsItemLayoutBinding) : RecyclerView.ViewHolder(layoutBinding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(newsModel: NewsModel) {



            layoutBinding.titleText.text = newsModel.title
            layoutBinding.publishedText.text = newsModel.publishedBy
            layoutBinding.dateText.text = Utils.dateFormat(newsModel.publishDate)
            GlideApp.with(layoutBinding.root.context)
                .load(newsModel.multimedia.first().url)
                .placeholder(R.drawable.news_placeholder)
                .into(layoutBinding.newsPhoto)

            layoutBinding.root.setOnClickListener {
                onItemClicked(newsModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val newsItemBinding = NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(newsItemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
}