package com.example.news.repository.headlines

import com.example.news.data.HeadLines
import com.example.news.util.Resource
import kotlinx.coroutines.flow.Flow

interface HeadLinesRepository {

    suspend fun insertHeadLines(headLines: List<HeadLines>)

    fun getAllHeadLines(category:String,page:Int): Flow<Resource<List<HeadLines>?>>

    fun getAllHeadLinesDB(category:String,page:Int): Flow<Resource<List<HeadLines>?>>

}