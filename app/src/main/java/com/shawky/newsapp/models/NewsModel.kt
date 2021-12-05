package com.shawky.newsapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiModel(
    val status : String,
    val results : ArrayList<NewsModel>
)

@Serializable
data class NewsModel(
    val title: String,
    @SerialName("published_date")
    val publishDate: String,
    val multimedia: ArrayList<MultiMedia>,
    val abstract: String,
    @SerialName("byline")
    val publishedBy : String
) : java.io.Serializable

@Serializable
data class MultiMedia(
    val url : String,
    val copyright : String
)
