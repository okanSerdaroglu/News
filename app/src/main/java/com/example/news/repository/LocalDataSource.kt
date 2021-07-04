package com.example.news.repository

import com.example.news.data.local.headlines.HeadLinesCacheEntity
import com.example.news.data.local.news.NewsCacheEntity
import com.example.news.db.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(
    private val newsDao: NewsDao
) {

    suspend fun insertNews(newsCacheEntity: NewsCacheEntity) = newsDao.insertNews(newsCacheEntity)

    fun getAllNews(): Flow<List<NewsCacheEntity>?> = newsDao.getAllNews()

    suspend fun insertHeadLines(headLinesCacheEntity: HeadLinesCacheEntity) =
        newsDao.insertHeadLines(headLinesCacheEntity)

    fun getAllHeadLines(category: String, page: Int): Flow<List<HeadLinesCacheEntity>?> =
        newsDao.getAllHeadLines()

}