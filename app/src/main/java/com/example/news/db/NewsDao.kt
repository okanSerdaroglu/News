package com.example.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.local.NewsCacheEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsCacheEntity)

    @Query("SELECT * FROM news_header")
    fun getAllNews(): Flow<List<NewsCacheEntity>?>

}