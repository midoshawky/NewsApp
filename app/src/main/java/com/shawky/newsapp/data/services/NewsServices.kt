package com.shawky.newsapp.data.services

import android.util.Log
import com.shawky.newsapp.constants.routs.ApiRouts
import com.shawky.newsapp.models.ApiModel
import com.shawky.newsapp.models.NewsModel
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.json.JsonObject
import java.lang.Exception

class NewsServices(val client:HttpClient) {

    suspend fun getAllNews() : ArrayList<NewsModel> {
        return try {
          val data = client.get<ApiModel>(ApiRouts.WORLD_NEWS_URl)

                data.results
        }catch (e:Exception){
            Log.i("Api","Err : ${e.message}")
            return arrayListOf()
        }
    }

}