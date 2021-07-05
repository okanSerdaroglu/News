package com.example.news.repository.headlines

import com.example.news.data.HeadLines
import com.example.news.data.local.headlines.HeadLinesCacheMapper
import com.example.news.data.remote.headlines.HeadLinesCallMapper
import com.example.news.repository.LocalDataSource
import com.example.news.repository.RemoteDataSource
import com.example.news.util.Constants
import com.example.news.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeadLinesRepositoryImpl
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val cacheMapper: HeadLinesCacheMapper,
    private val callMapper: HeadLinesCallMapper
) : HeadLinesRepository {
    override suspend fun insertHeadLines(headLines: List<HeadLines>) {
        headLines.map {
            localDataSource.insertHeadLines(cacheMapper.mapToEntity(it))
        }
    }

    override fun getAllHeadLines(category: String, page: Int): Flow<Resource<List<HeadLines>?>> =
        flow {
            emit(Resource.loading())
            val response = remoteDataSource.getAllHeadLines(category, page)
            if (response.isSuccessful) {
                response.body()?.let { headLinesResponse ->
                    headLinesResponse.articles?.let { results ->
                        val headLines = results.map {
                            callMapper.mapFromEntity(it)
                        }
                        insertHeadLines(headLines)
                        getAllHeadLinesDB(category, page).collect {
                            emit(it)
                        }
                    } ?: getAllHeadLinesDB(category, page).collect {
                        emit(it)
                    }
                } ?: getAllHeadLinesDB(category, page).collect {
                    emit(it)
                }
            } else {
                getAllHeadLinesDB(category, page).collect {
                    emit(it)
                }
            }
        }

    override fun getAllHeadLinesDB(
        category: String,
        page: Int
    ): Flow<Resource<List<HeadLines>?>> = flow {
        localDataSource.getAllHeadLines(category, page).collect { cacheHeadLines ->
            if (!cacheHeadLines.isNullOrEmpty()) {
                val headLines = cacheHeadLines.map {
                    cacheMapper.mapFromEntity(it)
                }
                emit(Resource.success(headLines))
            } else {
                emit(Resource.error(Constants.HEADLINES_NOT_FOUND, null))
            }
        }
    }

    override suspend fun updateHeadLinesReadListStatus(isInReadList: Boolean, title: String) : Int {
        return withContext(Dispatchers.IO){
            localDataSource.updateReadList(isInReadList = isInReadList, title = title)
        }
    }

}