package com.shawky.newsapp.data.services

import android.util.Log
import com.shawky.newsapp.constants.routs.ApiRouts
import com.shawky.newsapp.models.ApiModel
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

enum class State {
    LOADING,
    LOADED,
    ERROR
}


class NewsServices @Inject constructor(private val client:HttpClient) {

    suspend fun getAllNews() : ApiState {
        return try {
          val data = client.get<ApiModel>(ApiRouts.WORLD_NEWS_URl)
             ApiState(State.LOADED,data.results)
        }catch (e:Exception){
            Log.i("Api","Err : ${e.message}")
            return ApiState(State.ERROR,e.message)
        }
    }

}

class ApiState (val state: State = State.LOADING , val data : Any? = null)