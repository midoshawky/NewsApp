package com.shawky.newsapp.dependcies

import com.shawky.newsapp.data.repos.NewsRemoteRepo
import com.shawky.newsapp.data.repos.NewsRepository
import com.shawky.newsapp.data.services.NewsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsViewModelModules {

    @Provides
    @Singleton
    fun newsRepoProvider(newsServices: NewsServices) : NewsRepository {
        return NewsRemoteRepo(newsServices)
    }


}