package com.example.news.di

import android.content.Context
import androidx.room.Room
import com.example.news.BuildConfig
import com.example.news.data.remote.NewsAPI
import com.example.news.db.NewsDatabase
import com.example.news.util.Constants.DATABASE_NAME
import com.example.news.util.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context = context


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
    fun provideNewsApi(
        okHttpClient: OkHttpClient
    ): NewsAPI =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(NewsAPI::class.java)


    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: Interceptor,
        networkInterceptor: NoConnectionInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(logger)
        }
        return okHttpClient
            .addInterceptor(networkInterceptor)
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val original: Request = chain.request()

        val url = original.url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()

        val req = original
            .newBuilder()
            .url(url)
            .build()

        chain.proceed(req)
    }

}