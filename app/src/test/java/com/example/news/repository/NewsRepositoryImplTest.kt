package com.example.news.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.MainCoroutineRule
import com.example.news.SharedModel
import com.example.news.data.local.NewsCacheMapper
import com.example.news.data.remote.NewsCallMapper
import com.example.news.data.remote.NewsHeadersResponse
import com.example.news.util.Constants
import com.example.news.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var cacheMapper: NewsCacheMapper

    @Mock
    private lateinit var callMapper: NewsCallMapper


    private lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        newsRepository = NewsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            cacheMapper = cacheMapper,
            callMapper = callMapper
        )
    }

    @Test
    fun insertNews() = runBlockingTest {
        val news = listOf(SharedModel.news, SharedModel.news_2)

        `when`(cacheMapper.mapToEntity(SharedModel.news)).thenReturn(SharedModel.cacheNews)
        `when`(cacheMapper.mapToEntity(SharedModel.news_2)).thenReturn(SharedModel.cacheNews_2)

        newsRepository.insertNews(news)

        Mockito.verify(localDataSource).insertNews(SharedModel.cacheNews)
        Mockito.verify(localDataSource).insertNews(SharedModel.cacheNews_2)
    }

    @Test
    fun `getAllNews success`() = runBlockingTest {

        val response = NewsHeadersResponse(listOf(SharedModel.callNews, SharedModel.callNews_2))
        `when`(remoteDataSource.getAllNews(1)).thenReturn(Response.success(response))
        `when`(callMapper.mapFromEntity(SharedModel.callNews)).thenReturn(SharedModel.news)
        `when`(callMapper.mapFromEntity(SharedModel.callNews_2)).thenReturn(SharedModel.news_2)

        val result = newsRepository.getAllNews(1).drop(1).first()
        assertThat(result.data).isEqualTo(listOf(SharedModel.news, SharedModel.news_2))
    }

    @Test
    fun `getAllNews method gets news successfully with an empty body`() = runBlockingTest {

        `when`(remoteDataSource.getAllNews(1))
            .thenReturn(Response.success<NewsHeadersResponse>(200, null))

        val flow = flow {
            emit(listOf(SharedModel.cacheNews, SharedModel.cacheNews_2))
        }

        `when`(localDataSource.getAllNews(1)).thenReturn(flow)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews)).thenReturn(SharedModel.news)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews_2)).thenReturn(SharedModel.news_2)

        val result = newsRepository.getAllNews(1).drop(1).first()
        assertThat(result.data).isEqualTo(listOf(SharedModel.news, SharedModel.news_2))

    }

    @Test
    fun `getting all news with an error`() = runBlockingTest {

        `when`(remoteDataSource.getAllNews(1))
            .thenReturn(Response.error(400, "".toResponseBody()))

        val flow = flow {
            emit(listOf(SharedModel.cacheNews, SharedModel.cacheNews_2))
        }

        `when`(localDataSource.getAllNews(1)).thenReturn(flow)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews)).thenReturn(SharedModel.news)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews_2)).thenReturn(SharedModel.news_2)

        val result = newsRepository.getAllNews(1).drop(1).first()
        assertThat(result.data).isEqualTo(listOf(SharedModel.news, SharedModel.news_2))
    }


    @Test
    fun `getting all news from DB successfully`() = runBlockingTest {
        val flow = flow {
            emit(listOf(SharedModel.cacheNews, SharedModel.cacheNews_2))
        }

        `when`(localDataSource.getAllNews(1)).thenReturn(flow)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews)).thenReturn(SharedModel.news)
        `when`(cacheMapper.mapFromEntity(SharedModel.cacheNews_2)).thenReturn(SharedModel.news_2)

        val result = newsRepository.getAllNewsDB(1).first()

        assertThat(result.data).isEqualTo(listOf(SharedModel.news, SharedModel.news_2))
    }

    @Test
    fun `getAllNewsDb news not found`() = runBlockingTest {
        val flow = flow {
            emit(null)
        }

        `when`(localDataSource.getAllNews(1)).thenReturn(flow)

        val result = newsRepository.getAllNewsDB(1).first()
        val expected = Resource.error(Constants.NEWS_NOT_FOUND, null)
        assertThat(result).isEqualTo(expected)
    }

}