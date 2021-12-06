package com.shawky.newsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawky.newsapp.data.repos.NewsRemoteRepo
import com.shawky.newsapp.data.services.ApiState
import com.shawky.newsapp.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo : NewsRemoteRepo) : ViewModel() {
    private val newsMutableLiveData : MutableLiveData<ArrayList<NewsModel>> = MutableLiveData()
    private val newsState : MutableLiveData<ApiState> = MutableLiveData()
    val newsStateLiveData : LiveData<ApiState> = newsState
    init {
        viewModelScope.launch {
            val dataList : ArrayList<NewsModel> = ArrayList()
            Log.i("News","News Data : $dataList")
            newsMutableLiveData.value = dataList
            newsState.value = repo.getNews()
        }
    }

}