package com.shawky.newsapp.constants.routs

object ApiRouts {
    private const val API_KEY = "?api-key=Gxur3a3OmOQu89bRGAwIuqlOTgPwSUqq"
    private const val MAIN_URL = "https://api.nytimes.com/svc/topstories/v2"
    const val WORLD_NEWS_URl = "$MAIN_URL/world.json$API_KEY"
}