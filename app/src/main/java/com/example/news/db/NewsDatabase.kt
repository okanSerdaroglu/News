package com.example.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.local.NewsCacheEntity

@Database(
    entities = [NewsCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}