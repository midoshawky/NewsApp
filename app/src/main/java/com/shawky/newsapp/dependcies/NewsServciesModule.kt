package com.shawky.newsapp.dependcies

import com.shawky.newsapp.data.services.NewsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsServicesModule {
    @Provides
    @Singleton
    fun provideServices(client: HttpClient) : NewsServices {
        return  NewsServices(client)
    }

}