package com.example.news.repository

import com.example.news.data.News
import com.example.news.data.local.NewsCacheEntity
import com.example.news.data.local.NewsCacheMapper
import com.example.news.data.remote.NewsCallMapper
import com.example.news.util.Constants
import com.example.news.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val cacheMapper: NewsCacheMapper,
    private val callMapper: NewsCallMapper
) : NewsRepository {
    override suspend fun insertNews(newsCacheEntity: NewsCacheEntity) {
        localDataSource.insertNews(newsCacheEntity)
    }

    override fun getAllNews(page: Int): Flow<Resource<List<News>>> = flow {
        emit(Resource.loading(null))
        val response = remoteDataSource.getAllNews(page = page)
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                if (!newsResponse.results.isNullOrEmpty()) {
                    val news = newsResponse.results.map { callNews ->
                        callMapper.mapFromEntity(callNews)
                    }
                    emit(Resource.success(news))
                } else {
                    getAllNewsDB(page = page)
                }
            } ?: getAllNewsDB(page = page)
        } else {
            getAllNewsDB(page = page)
        }
    }

    override fun getAllNewsDB(page: Int): Flow<Resource<List<News>>> = flow {
        localDataSource.getAllNews(page).collect { cacheNews ->
            val news = cacheNews.map {
                cacheMapper.mapFromEntity(it)
            }
            if (news.isNullOrEmpty()) {
                emit(Resource.error(Constants.NEWS_NOT_FOUND, null))
            } else {
                emit(Resource.success(news))
            }
        }
    }
}