package com.example.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.local.headlines.HeadLinesCacheEntity
import com.example.news.data.local.news.NewsCacheEntity

@Database(
    entities = [
        NewsCacheEntity::class,
        HeadLinesCacheEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}