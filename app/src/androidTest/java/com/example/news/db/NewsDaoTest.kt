package com.example.news.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.news.SharedModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsDaoTest {

    private lateinit var newsDatabase: NewsDatabase
    private lateinit var newsDao: NewsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        newsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        newsDao = newsDatabase.newsDao()
    }

    @Test
    fun insertNewsSuccess() = runBlockingTest {
        newsDao.insertNews(SharedModel.cacheNews)
        val news = newsDao.getAllNews().first()
        assertThat(news).contains(SharedModel.cacheNews)
    }

    @Test
    fun getAllNewsSuccess() = runBlockingTest {
        newsDao.insertNews(SharedModel.cacheNews)
        newsDao.insertNews(SharedModel.cacheNews_2)
        val news = newsDao.getAllNews().first()
        assertThat(news).hasSize(2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        newsDatabase.close()
    }

}