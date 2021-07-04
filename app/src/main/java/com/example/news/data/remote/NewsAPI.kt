package com.example.news.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/sources")
    suspend fun getNewsHeaders(): Response<NewsSourcesResponse>

}