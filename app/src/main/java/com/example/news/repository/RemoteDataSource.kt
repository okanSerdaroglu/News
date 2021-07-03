package com.example.news.repository

import com.example.news.data.remote.NewsAPI
import com.example.news.data.remote.NewsHeadersResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getAllNews(page: Int): Response<NewsHeadersResponse> = newsAPI.getNewsHeaders()
}