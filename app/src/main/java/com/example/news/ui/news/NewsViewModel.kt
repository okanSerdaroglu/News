package com.example.news.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.data.HeadLines
import com.example.news.data.News
import com.example.news.ui.news.usecases.headlines.GetHeadLinesUseCase
import com.example.news.ui.news.usecases.news.GetNewsUseCase
import com.example.news.util.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel
@ViewModelInject
constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getHeadLinesUseCase: GetHeadLinesUseCase
) : BaseViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    private val _headLinesList = MutableLiveData<List<HeadLines>>()
    val headLinesList: LiveData<List<HeadLines>> = _headLinesList

    fun getAllNews() {
        viewModelScope.launch {
            getNewsUseCase.getAllNews().collect { resourceListNews ->
                when (resourceListNews.status) {
                    is Status.SUCCESS -> {
                        _newsList.value = resourceListNews.data
                    }
                    else -> {
                        handleErrorLoadingResource(resourceListNews)
                    }
                }
            }
        }
    }

    fun getAllHeadLines() {
        viewModelScope.launch {
            getHeadLinesUseCase.getAllHeadLines().collect { resourceHeadLines ->
                when (resourceHeadLines.status) {
                    is Status.SUCCESS -> {
                        _headLinesList.value = resourceHeadLines.data
                    }
                    else -> {
                        handleErrorLoadingResource(resourceHeadLines)
                    }
                }
            }
        }
    }

}
