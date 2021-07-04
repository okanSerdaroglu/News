package com.example.news.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.MainCoroutineRule
import com.example.getOrAwaitValueTest
import com.example.news.SharedModel
import com.example.news.data.News
import com.example.news.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getNewsUseCase: GetNewsUseCase

    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setup() {
        newsViewModel = NewsViewModel(getNewsUseCase)
    }


    @Test
    fun `get all news show loading`() = runBlockingTest {
        val flow = flow {
            emit(Resource.loading(null))
        }

        `when`(getNewsUseCase.getAllNews()).thenReturn(flow)

        newsViewModel.getAllNews()

        val result = newsViewModel.mainStateView.getOrAwaitValueTest()
        assertThat(result.getContentIfNotHandled()?.isLoading).isTrue()
    }

    @Test
    fun `get all news show error`() = runBlockingTest {
        val flow = flow {
            val data: List<News> = emptyList()
            emit(Resource.error("Not found", data))
        }

        `when`(getNewsUseCase.getAllNews()).thenReturn(flow)

        newsViewModel.getAllNews()

        val result = newsViewModel.mainStateView.getOrAwaitValueTest()
        assertThat(result.getContentIfNotHandled()?.isError).isTrue()
    }

    @Test
    fun `get all news show success`() = runBlockingTest {
        val data: List<News> = listOf(SharedModel.news)

        val flow = flow {
            emit(Resource.success(data))
        }

        `when`(getNewsUseCase.getAllNews()).thenReturn(flow)

        newsViewModel.getAllNews()

        val result = newsViewModel.newsList.getOrAwaitValueTest()
        assertThat(result).isEqualTo(data)
    }

    @Test
    fun `get news detail show loading`() = runBlockingTest {
        val flow = flow {
            emit(Resource.loading(null))
        }

        `when`(getNewsUseCase.getAllNews()).thenReturn(flow)

        newsViewModel.getAllNews()

        val result = newsViewModel.mainStateView.getOrAwaitValueTest()
        assertThat(result.getContentIfNotHandled()?.isLoading).isTrue()
    }


}