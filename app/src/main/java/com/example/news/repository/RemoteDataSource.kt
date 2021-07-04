package com.example.news.repository

import com.example.news.data.remote.NewsAPI
import com.example.news.data.remote.NewsSourcesResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getAllNews(page: Int): Response<NewsSourcesResponse> = newsAPI.getNewsHeaders()
}