package com.example.news.repository.news

import com.example.news.data.News
import com.example.news.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun insertNews(news: List<News>)

    fun getAllNews(): Flow<Resource<List<News>?>>

    fun getAllNewsDB(): Flow<Resource<List<News>?>>

}