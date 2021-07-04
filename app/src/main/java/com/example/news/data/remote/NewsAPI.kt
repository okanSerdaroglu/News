package com.example.news.data.remote

import com.example.news.data.remote.headlines.HeadLinesResponse
import com.example.news.data.remote.news.NewsSourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/sources")
    suspend fun getNewsHeaders(): Response<NewsSourcesResponse>

    @GET("v2/top-headlines")
    suspend fun getAllHeadLines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Response<HeadLinesResponse>

}