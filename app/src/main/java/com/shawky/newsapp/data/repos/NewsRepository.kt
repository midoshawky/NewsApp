package com.shawky.newsapp.data.repos

import com.shawky.newsapp.data.services.ApiState

interface NewsRepository {
    suspend fun getNews() : ApiState
}