package com.example.news.repository.news

import com.example.news.data.News
import com.example.news.data.local.news.NewsCacheMapper
import com.example.news.data.remote.news.NewsCallMapper
import com.example.news.repository.LocalDataSource
import com.example.news.repository.RemoteDataSource
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
    override suspend fun insertNews(news: List<News>) {
        news.map {
            localDataSource.insertNews(cacheMapper.mapToEntity(it))
        }
    }

    override fun getAllNews(): Flow<Resource<List<News>?>> = flow {
        emit(Resource.loading())
        val response = remoteDataSource.getAllNews()
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                newsResponse.sources?.let { results ->
                    val news = results.map {
                        callMapper.mapFromEntity(it)
                    }
                    insertNews(news)
                    emit(Resource.success(news))
                } ?: getAllNewsDB().collect {
                    emit(it)
                }
            } ?: getAllNewsDB().collect {
                emit(it)
            }
        } else {
            getAllNewsDB().collect {
                emit(it)
            }
        }
    }

    override fun getAllNewsDB() = flow {
        localDataSource.getAllNews().collect { cacheNews ->
            if (!cacheNews.isNullOrEmpty()) {
                val news = cacheNews.map {
                    cacheMapper.mapFromEntity(it)
                }
                emit(Resource.success(news))
            } else {
                emit(Resource.error(Constants.NEWS_NOT_FOUND, null))
            }
        }
    }
}