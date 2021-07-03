package com.example.news.repository

import com.example.news.data.local.NewsCacheEntity
import com.example.news.db.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(
    private val newsDao: NewsDao
) {

    suspend fun insertNews(newsCacheEntity: NewsCacheEntity) = newsDao.insertNews(newsCacheEntity)

    fun getAllNews(page: Int): Flow<List<NewsCacheEntity>> = newsDao.getAllNews()

}