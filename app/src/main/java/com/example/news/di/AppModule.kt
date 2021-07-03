package com.example.news.di

import android.content.Context
import androidx.room.Room
import com.example.news.BuildConfig
import com.example.news.data.remote.NewsAPI
import com.example.news.db.NewsDatabase
import com.example.news.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNewsDao(
        database: NewsDatabase
    ) = database.newsDao()

    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(NewsAPI::class.java)

}