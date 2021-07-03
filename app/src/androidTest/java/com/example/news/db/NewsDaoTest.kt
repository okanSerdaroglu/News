package com.example.news.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.news.SharedModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NewsDaoTest {

    @Inject
    @Named("news_db")
    lateinit var newsDatabase: NewsDatabase

    private lateinit var newsDao: NewsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
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