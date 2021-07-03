package com.example.news.repository

import com.example.news.data.News
import com.example.news.data.local.NewsCacheEntity
import com.example.news.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun insertNews(news: List<News>)

    fun getAllNews(page: Int): Flow<Resource<List<News>>>

    fun getAllNewsDB(page: Int): Flow<Resource<List<News>>>

}