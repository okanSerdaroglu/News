package com.example.news.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.data.HeadLines
import com.example.news.data.News
import com.example.news.ui.headlines.GetHeadLinesUseCase
import com.example.news.ui.news.BaseViewModel
import com.example.news.ui.news.GetNewsUseCase
import com.example.news.util.Constants
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

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private var page = 1
    private var totalCount = 0

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

    fun getAllHeadLines(category: String) {
        viewModelScope.launch {
            getHeadLinesUseCase.getAllHeadLines(category, page).collect { resourceHeadLines ->
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

    fun setCategory(category: String) {
        _category.value = category
    }

    fun hasMoreItem(): Boolean {
        return (totalCount > page * Constants.PAGE_SIZE)
    }

    fun loadMoreItems() {
        page++
        _category.value?.let {
            getAllHeadLines(it)
        }
    }
}
