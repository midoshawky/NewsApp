package com.shawky.newsapp.data.repos

import com.shawky.newsapp.data.services.ApiState
import com.shawky.newsapp.data.services.NewsServices
import javax.inject.Inject


class NewsRemoteRepo @Inject constructor(private val newsData : NewsServices) : NewsRepository {
    override suspend fun getNews() : ApiState {
        return newsData.getAllNews()
    }
}