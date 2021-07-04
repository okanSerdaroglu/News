package com.example.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.local.headlines.HeadLinesCacheEntity
import com.example.news.data.local.news.NewsCacheEntity
import kotlinx.coroutines.flow.Flow
import javax.annotation.Nullable


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsCacheEntity)

    @Query("SELECT * FROM news_header")
    fun getAllNews(): Flow<List<NewsCacheEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeadLines(headLines: HeadLinesCacheEntity)

    @Query("SELECT * FROM headlines")
    fun getAllHeadLines(): Flow<List<HeadLinesCacheEntity>?>
}