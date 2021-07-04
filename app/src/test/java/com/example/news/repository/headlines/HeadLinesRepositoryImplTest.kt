package com.example.news.repository.headlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.MainCoroutineRule
import com.example.news.SharedModel
import com.example.news.data.local.headlines.HeadLinesCacheMapper
import com.example.news.data.remote.headlines.HeadLinesCallMapper
import com.example.news.data.remote.headlines.HeadLinesResponse
import com.example.news.repository.LocalDataSource
import com.example.news.repository.RemoteDataSource
import com.example.news.util.Constants
import com.example.news.util.Resource
import com.google.common.truth.Truth
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
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HeadLinesRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var cacheMapper: HeadLinesCacheMapper

    @Mock
    private lateinit var callMapper: HeadLinesCallMapper

    private val category = "general"
    private val page = 1

    private lateinit var headLinesRepository: HeadLinesRepository

    @Before
    fun setup() {
        headLinesRepository = HeadLinesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            cacheMapper = cacheMapper,
            callMapper = callMapper
        )
    }

    @Test
    fun insertHeadLines() = runBlockingTest {
        val headLines = listOf(SharedModel.headLines, SharedModel.headLines_2)

        Mockito.`when`(cacheMapper.mapToEntity(SharedModel.headLines))
            .thenReturn(SharedModel.cacheHeadLines)
        Mockito.`when`(cacheMapper.mapToEntity(SharedModel.headLines_2))
            .thenReturn(SharedModel.cacheHeadLines_2)

        headLinesRepository.insertHeadLines(headLines)

        Mockito.verify(localDataSource).insertHeadLines(SharedModel.cacheHeadLines)
        Mockito.verify(localDataSource).insertHeadLines(SharedModel.cacheHeadLines_2)
    }

    @Test
    fun `getAllHeadLines success`() = runBlockingTest {

        val response =
            HeadLinesResponse(10, listOf(SharedModel.callHeadLines, SharedModel.callHeadLines_2))
        Mockito.`when`(remoteDataSource.getAllHeadLines(category, page))
            .thenReturn(Response.success(response))
        Mockito.`when`(callMapper.mapFromEntity(SharedModel.callHeadLines))
            .thenReturn(SharedModel.headLines)
        Mockito.`when`(callMapper.mapFromEntity(SharedModel.callHeadLines_2))
            .thenReturn(SharedModel.headLines_2)

        val result = headLinesRepository.getAllHeadLines(category, page).drop(1).first()
        Truth.assertThat(result.data)
            .isEqualTo(listOf(SharedModel.headLines, SharedModel.headLines_2))
    }

    @Test
    fun `getAllHeadLines method gets news successfully with an empty body`() = runBlockingTest {

        Mockito.`when`(remoteDataSource.getAllHeadLines(category, page))
            .thenReturn(Response.success<HeadLinesResponse>(200, null))

        val flow = flow {
            emit(listOf(SharedModel.cacheHeadLines, SharedModel.cacheHeadLines_2))
        }

        Mockito.`when`(localDataSource.getAllHeadLines(category, page)).thenReturn(flow)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines))
            .thenReturn(SharedModel.headLines)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines_2))
            .thenReturn(SharedModel.headLines_2)

        val result = headLinesRepository.getAllHeadLines(category, page).drop(1).first()
        Truth.assertThat(result.data)
            .isEqualTo(listOf(SharedModel.headLines, SharedModel.headLines_2))

    }

    @Test
    fun `getting all headLines with an error`() = runBlockingTest {

        Mockito.`when`(remoteDataSource.getAllHeadLines(category, page))
            .thenReturn(Response.error(400, "".toResponseBody()))

        val flow = flow {
            emit(listOf(SharedModel.cacheHeadLines, SharedModel.cacheHeadLines_2))
        }

        Mockito.`when`(localDataSource.getAllHeadLines(category, page)).thenReturn(flow)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines))
            .thenReturn(SharedModel.headLines)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines_2))
            .thenReturn(SharedModel.headLines_2)

        val result = headLinesRepository.getAllHeadLines(category, page).drop(1).first()
        Truth.assertThat(result.data)
            .isEqualTo(listOf(SharedModel.headLines, SharedModel.headLines_2))
    }


    @Test
    fun `getting all headLines from DB successfully`() = runBlockingTest {
        val flow = flow {
            emit(listOf(SharedModel.cacheHeadLines, SharedModel.cacheHeadLines_2))
        }

        Mockito.`when`(localDataSource.getAllHeadLines(category, page)).thenReturn(flow)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines))
            .thenReturn(SharedModel.headLines)
        Mockito.`when`(cacheMapper.mapFromEntity(SharedModel.cacheHeadLines_2))
            .thenReturn(SharedModel.headLines_2)

        val result = headLinesRepository.getAllHeadLinesDB(category, page).first()

        Truth.assertThat(result.data)
            .isEqualTo(listOf(SharedModel.headLines, SharedModel.headLines_2))
    }

    @Test
    fun `getAllHeadLinesDb news not found`() = runBlockingTest {
        val flow = flow {
            emit(null)
        }

        Mockito.`when`(localDataSource.getAllHeadLines(category, page)).thenReturn(flow)

        val result = headLinesRepository.getAllHeadLinesDB(category, page).first()
        val expected = Resource.error(Constants.HEADLINES_NOT_FOUND, null)
        Truth.assertThat(result).isEqualTo(expected)
    }

}