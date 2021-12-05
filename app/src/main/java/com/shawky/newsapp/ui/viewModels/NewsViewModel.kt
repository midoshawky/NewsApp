package com.shawky.newsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawky.newsapp.data.services.NewsServices
import com.shawky.newsapp.dependcies.AppModule
import com.shawky.newsapp.models.NewsModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsMutableLiveData : MutableLiveData<ArrayList<NewsModel>> = MutableLiveData()
    val newsLiveData : LiveData<ArrayList<NewsModel>> = newsMutableLiveData

    init {
        val data = NewsServices(AppModule.initializeClient())
        viewModelScope.launch {
            val dataList : ArrayList<NewsModel> = ArrayList()
            Log.i("News","News Data : $dataList")
            data.getAllNews().filter { obj -> obj.title.isNotEmpty() && obj.abstract.isNotEmpty()  }.toCollection(dataList)
            newsMutableLiveData.value = dataList
        }
    }

}