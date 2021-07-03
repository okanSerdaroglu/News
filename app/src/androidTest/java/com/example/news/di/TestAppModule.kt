package com.example.news.di

import android.content.Context
import androidx.room.Room
import com.example.news.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object TestAppModule {
    @Provides
    @Named("news_db")
    fun provideNewsDatabase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        NewsDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
}