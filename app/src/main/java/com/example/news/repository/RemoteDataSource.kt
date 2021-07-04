package com.example.news.repository

import com.example.news.data.remote.NewsAPI
import com.example.news.data.remote.headlines.HeadLinesResponse
import com.example.news.data.remote.news.NewsSourcesResponse
import com.example.news.util.Constants
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(
    private val newsAPI: NewsAPI,
) {
    suspend fun getAllNews(): Response<NewsSourcesResponse> = newsAPI.getNewsHeaders()

    suspend fun getAllHeadLines(category: String, page: Int)
            : Response<HeadLinesResponse> = newsAPI.getAllHeadLines(
        category = category,
        page = page,
        pageSize = Constants.PAGE_SIZE
    )
}